package cn.est.controller;

import cn.est.constants.Constants;
import cn.est.dto.Result;
import cn.est.constants.ResultEnum;
import cn.est.utils.ResultUtils;
import cn.est.dto.MaintainOrderDto;
import cn.est.pojo.PayOrder;
import cn.est.service.AlipayBean;
import cn.est.service.AlipayConfig;
import cn.est.service.AlipaySerevice;
import cn.est.service.PayOrderService;
import cn.est.service.MaintainOrderService;
import cn.est.service.UserService;
import cn.est.dto.BaseException;
import com.alipay.api.AlipayApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @Description 支付控制器
 * @Date 2019-08-19 13:33
 * @Author Liujx
 * Version 1.0
 **/
@Api(description = "支付控制器")
@RestController
@RequestMapping("/api/pay")
public class PayController {

    Logger log = LoggerFactory.getLogger(PayController.class);

    @Autowired
    private MaintainOrderService maintainOrderService;

    @Autowired
    private AlipayConfig alipayConfig;

    @Autowired
    private UserService userService;

    @Autowired
    private AlipaySerevice alipaySerevice;

    @Autowired
    private PayOrderService payOrderService;

    /**
     * 发起支付请求
     * @param orderNo
     * @return
     */
    @ApiOperation(value = "发起支付请求", produces = "application/json", notes = "支付宝支付，发起支付请求")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "token", value = "用户token", required = true, dataType = "String")
            ,@ApiImplicitParam(name = "orderNo", value = "订单号", required = true, dataType = "String")})
    @RequestMapping("/aliPay")
    public String alipay(String orderNo) throws BaseException {
        MaintainOrderDto maintainOrderDto = maintainOrderService.getByNo(orderNo);
        Result result = checkOrder(maintainOrderDto);
        if(!result.code.equals(ResultEnum.SUCCESS.getCode())){
            log.info("订单支付参数错误orderNo:{},amount:{}", orderNo);
            throw new BaseException(result);
        }
        AlipayBean alipayBean = new AlipayBean();
        alipayBean.setSubject("维修订单");
//        alipayBean.setBody(body);
        alipayBean.setProduct_code(alipayConfig.getProduct_code());
        alipayBean.setTimeout_express(alipayConfig.getTimeout_express());
        return alipaySerevice.pay(alipayBean, maintainOrderDto);
    }

    /**
     * 导步通知，跟踪支付状态变更
     * @param request
     * @param response
     */
    @PostMapping("/alipay/notify")
    public void trackPaymentStatus(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            //商户订单号
            String out_trade_no = request.getParameter("out_trade_no");
            PayOrder payOrder = payOrderService.getByPayNo(out_trade_no);
            if(payOrder != null && payOrder.getStatus().equals(Constants.Order.PayStaus.SUCCESS)){
                //跳转到支付成功页面
                response.getWriter().println("success"); // 请不要修改或删除
            }else {
                int succ = alipaySerevice.asyncNotify(request);
                if (succ == 0) {// 验证失败
                    response.getWriter().println("fail");
                } else {// 验证成功
                    response.getWriter().println("success"); // 请不要修改或删除
                }
            }

        } catch (UnsupportedEncodingException e) {
            log.error("支付宝异步回调错误:{}", e);
        } catch (AlipayApiException e) {
            log.error("支付宝异步回调错误:{}", e);
        } catch (IOException e) {
            log.error("支付宝异步回调错误:{}", e);
        } catch (Exception e) {
            log.error("支付宝异步回调错误:{}", e);
        }
    }

    /**
     * 支付宝页面跳转同步通知页面
     */
    @GetMapping("/alipay/return")
    public void callback(HttpServletRequest request, HttpServletResponse response) throws BaseException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            //商户订单号
            String out_trade_no = request.getParameter("out_trade_no");
            PayOrder payOrder = payOrderService.getByPayNo(out_trade_no);
            if(payOrder != null && payOrder.getStatus().equals(Constants.Order.PayStaus.SUCCESS)){
                //跳转到支付成功页面
                response.sendRedirect(String.format(alipayConfig.getPaymentSuccessUrl(), out_trade_no));
            }else{
                int succ = alipaySerevice.syncNotify(request);
                if(succ == 0){
                    //提示支付失败
                    response.sendRedirect(alipayConfig.getPaymentFailureUrl());
                }else{
                    //提示支付成功
                    response.sendRedirect(String.format(alipayConfig.getPaymentSuccessUrl(), out_trade_no));
                }
            }
        } catch (UnsupportedEncodingException e) {
            log.error("支付宝同步回调错误:{}", e);
            throw new BaseException(ResultEnum.FAIL);
        } catch (AlipayApiException e) {
            log.error("支付宝同步回调错误:{}", e);
            throw new BaseException(ResultEnum.FAIL);
        } catch (IOException e) {
            log.error("支付宝同步回调错误:{}", e);
            throw new BaseException(ResultEnum.FAIL);
        }
    }


    /**
     * 校验支付信息
     * @param order
     */
    private Result checkOrder(MaintainOrderDto order) throws BaseException {
        boolean flag = false;
        Result result = ResultUtils.returnSuccess();
        if(order == null){
            log.info("订单不存在");
            result = ResultUtils.returnFail("订单不存在", ResultEnum.FAIL_PARAM.getCode());
            return  result;
        }
        // 暂时只有订金支付
        if(!order.getStatus().equals(Constants.Order.MaintainStatus.APPOINT)){
            log.info("订单状态不可支付status:{}", order.getStatus());
            result = ResultUtils.returnFail("订单状态不可支付", ResultEnum.FAIL_PARAM.getCode());
            return  result;
        }
        return result;
    }
}
