package cn.est.service.impl;

import cn.est.constants.Constants;
import cn.est.utils.DateUtils;
import cn.est.utils.MathUtils;
import cn.est.mapper.PayOrderMapper;
import cn.est.dto.MaintainOrderDto;
import cn.est.constants.PayChannelEnum;
import cn.est.pojo.PayOrder;
import cn.est.service.PayOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description @Description 支付订单业务层实现
 * @Date 2019-08-22 16:19
 * @Author Liujx
 * Version 1.0
 **/
@Service
@Transactional
public class PayOrderServiceImpl implements PayOrderService {

    Logger log = LoggerFactory.getLogger(PayOrderServiceImpl.class);

    @Autowired
    private PayOrderMapper payOrderMapper;


    /**
     * 保存交易支付订单
     * @param maintainOrder
     * @param payChannelEnum
     * @param price
     * @return
     * @
     */
    @Override
    public PayOrder save(MaintainOrderDto maintainOrder, PayChannelEnum payChannelEnum, BigDecimal price)  {

        String payNo = maintainOrder.getOrderNo();
        Integer payType = null;
        if(maintainOrder.getStatus().equals(Constants.Order.MaintainStatus.APPOINT)){
            payType = Constants.Order.MaintainStatus.PAY_DEPOSIT;
        }
        // 创建支付订单编号啊
        payNo = createPayNo(payNo, payType);
        PayOrder payOrder = new PayOrder(maintainOrder.getOrderNo(), payNo ,maintainOrder.getUserId()
                , price, payType, payChannelEnum.getCode());
        payOrderMapper.insertPayOrder(payOrder);
        return payOrder;
    }

    /**
     * 支付完成，修改订单状态
     * @param payOrder
     * @param aliTradeNo
     */
    @Override
    public void finishPay(PayOrder payOrder, String aliTradeNo) {
        Date currentTime = DateUtils.getCurrentDate(DateUtils.YYYY_MM_DD_HH_MM_SS);
        payOrder.setAliTradeNo(aliTradeNo);
        payOrder.setStatus(Constants.Order.PayStaus.SUCCESS);
        payOrder.setUpdatedUserId(payOrder.getUserId());
        payOrder.setUpdatedTime(currentTime);
        payOrderMapper.updatePayOrder(payOrder);
    }


    /**
     * 根据商户支付订单号查询支付信息
     * @param payNo
     * @return
     */
    @Override
    public PayOrder getByPayNo(String payNo) {
        return payOrderMapper.selectByPayNo(payNo);
    }

    /**
     * 创建支付订单编号
     * 支付订单编号根据订单编号 + 随机数 + 状态类型 生成
     * @param payNo
     * @param payType
     * @return
     */
    private String createPayNo(String payNo, Integer payType) {
        StringBuffer buffer = new StringBuffer(payNo);
        buffer.append(MathUtils.random());
        buffer.append(payType);
        log.info("订单：{}当前状态为：{}，生成支付订单编号：{}", payNo, payType, buffer);
        PayOrder payOrder = payOrderMapper.selectByPayNo(buffer.toString());
        // 如果已经存在，那么重新生成一个支付订单编号
        if(payOrder != null){
            return createPayNo(payNo, payType);
        }
        return  buffer.toString();
    }

}
