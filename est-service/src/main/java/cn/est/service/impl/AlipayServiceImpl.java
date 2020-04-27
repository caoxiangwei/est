package cn.est.service.impl;

import cn.est.mapper.PayOrderMapper;
import cn.est.dto.MaintainOrderDto;
import cn.est.constants.PayChannelEnum;
import cn.est.pojo.PayOrder;
import cn.est.service.AlipayBean;
import cn.est.service.AlipayConfig;
import cn.est.service.AlipaySerevice;
import cn.est.service.PayOrderService;
import cn.est.service.MaintainOrderService;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Description 支付宝支付业务层实现
 * @Date 2019-08-20 15:44
 * @Author Liujx
 * Version 1.0
 **/
@Service
@Transactional
public class AlipayServiceImpl implements AlipaySerevice {

    Logger log = LoggerFactory.getLogger(AlipayServiceImpl.class);

    @Autowired
    private AlipayConfig alipayConfig;

    @Autowired
    private MaintainOrderService maintainOrderService;

    @Autowired
    private PayOrderService payOrderService;

    @Autowired
    private PayOrderMapper payOrderMapper;

    /**
     * 支付接口
     * @param alipayBean
     * @param maintainOrder
     * @return
     * @throws AlipayApiException
     */
    public String pay(AlipayBean alipayBean, MaintainOrderDto maintainOrder) {
        String result = null;
        try {
            // 1、获得初始化的AlipayClient
            String serverUrl = alipayConfig.getUrl();                   // 支付宝支付地址
            String appId = alipayConfig.getAppId();                     // appID
            String privateKey = alipayConfig.getPrivateKey();           // 私钥
            String alipayPublicKey = alipayConfig.getPublicKey();       // 公钥
            String format = alipayConfig.getFormat();                   // 数据格式
            String charset = alipayConfig.getCharset();                 // 编码格式
            String signType = alipayConfig.getSignType();               // sign类型
            String returnUrl = alipayConfig.getReturnUrl();             // 同步回调地址
            String notifyUrl = alipayConfig.getNotifyUrl();             // 异步回调地址
            // ali支付请求对象
            AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset, alipayPublicKey, signType);
            // 2、设置请求参数
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
            // 页面跳转同步通知页面路径
            alipayRequest.setReturnUrl(returnUrl);
            // 服务器异步通知页面路径
            alipayRequest.setNotifyUrl(notifyUrl);
            // 保存支付订单信息
            PayOrder payOrder = payOrderService.save(maintainOrder, PayChannelEnum.ALIPAY, maintainOrder.getSubscription());
            alipayBean.setOut_trade_no(payOrder.getPayNo());
            alipayBean.setTotal_amount(maintainOrder.getSubscription().toString());
            // 封装参数
            alipayRequest.setBizContent(JSON.toJSONString(alipayBean));
            // 3、请求支付宝进行付款，并获取支付结果
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (Exception e) {
            log.error("唤起支付宝支付页面错误:{}", e);
        }
        // 返回付款信息
        return result;
    }

    /**
     * 阿里支付同步回调处理
     * @param request
     * @return
     * @
     */
    @Override
    public int syncNotify(HttpServletRequest request) throws AlipayApiException {
        int succ = 0;
        Map requestParams = request.getParameterMap();
        // 商户订单号--支付订单号
        String out_trade_no = request.getParameter("out_trade_no");
        // 商户appId
        String app_id = request.getParameter("app_id");
        // 支付宝交易号
        String trade_no = request.getParameter("trade_no");
        // 支付宝交易金额
        String total_amount = request.getParameter("total_amount");
        // 支付信息校验
        boolean verify_result  = checkPayInfo(out_trade_no, total_amount, app_id, requestParams);
        if(verify_result){
            // 支付签名校验
            verify_result = verifyResult(requestParams);
        }
        if (verify_result) {// 验证成功
            log.info("支付宝支付验证【成功】");
            succ = maintainOrderService.finishPay(trade_no, out_trade_no, total_amount);
        } else {// 验证失败
            log.info("支付宝支付验证【失败】");
        }
        return succ;
    }


    /**
     * 阿里支付回调处理
     * @param request
     * @return
     */
    @Override
    public int asyncNotify(HttpServletRequest request) throws AlipayApiException {
        int succ = 0;
        Map requestParams = request.getParameterMap();
        // 商户订单号
        String out_trade_no = request.getParameter("out_trade_no");
        // 商户appId
        String app_id = request.getParameter("app_id");
        // 支付宝交易号
        String trade_no = request.getParameter("trade_no");
        // 支付宝交易金额
        String total_amount = request.getParameter("total_amount");
        // 交易状态
        String trade_status = request.getParameter("trade_status");
        // 交易状态
        if(!trade_status.equals("TRADE_SUCCESS")){
            log.info("阿里支付错误，交易状态错误trade_status:{}", trade_status);
            return 0;
        }
        // 支付信息校验
        boolean verify_result  = checkPayInfo(out_trade_no, total_amount, app_id, requestParams);
        if(verify_result){
            // 支付签名校验
            verify_result = verifyResult(requestParams);
        }
        if (verify_result) {// 验证成功
            log.info("支付宝支付验证【成功】");
            succ = maintainOrderService.finishPay(trade_no, out_trade_no, total_amount);
        } else {// 验证失败
            log.info("支付宝支付验证【失败】");
        }
        return succ;
    }

    /**
     * 校验支付信息
     * @param out_trade_no
     * @param total_amount
     * @param app_id
     * @return
     * @
     */
    private boolean checkPayInfo(String out_trade_no, String total_amount, String app_id
            , Map<String, Object> requestParams) {
        boolean flag = false;
        // 商户订单号
        PayOrder payOrder = payOrderMapper.selectByPayNo(out_trade_no);
        if(payOrder == null ){
            log.info("支付订单payNo:{}不存在", out_trade_no);
            return false;
        }
        // appId
        if(!app_id.equals(alipayConfig.getAppId())){
            log.info("阿里支付错误，支付账号错误：支付订单app_id:{},订单app_id：{}", app_id, alipayConfig.getAppId());
            return false;
        }
        // 支付宝交易金额
        if(!payOrder.getPrice().equals(new BigDecimal(total_amount))){
            log.info("阿里支付错误，支付金额与订单金额不符total_amount:{},订单金额：{}", total_amount, payOrder.getPrice());
            return false;
        }
        flag = true;
        return  flag;
    }


    /**
     * 校验支付宝支付返回结果
     * @param requestParams
     * @return
     * @
     */
    public boolean verifyResult(Map<String, String[]>  requestParams) throws AlipayApiException {
        Map<String, String> verifyParams = new HashMap<String, String>();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            verifyParams.put(name, valueStr);
        }
        // 计算得出通知验证结果
        boolean verify_result = AlipaySignature.rsaCheckV1(verifyParams, alipayConfig.getAccountPublicKey(),
                alipayConfig.getCharset(), alipayConfig.getSignType());
        return verify_result;
    }


}
