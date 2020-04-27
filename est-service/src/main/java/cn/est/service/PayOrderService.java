package cn.est.service;

import cn.est.dto.MaintainOrderDto;
import cn.est.constants.PayChannelEnum;
import cn.est.pojo.PayOrder;

import java.math.BigDecimal;

/**
 * @Description 支付订单业务层接口
 * @Date 2019-08-22 16:18
 * @Author Liujx
 * Version 1.0
 **/
public interface PayOrderService {

    /**
     * 根据订单信息保存支付订单信息
     * @param maintainOrder
     * @param payChannelEnum
     * @param price
     * @return
     */
    PayOrder save(MaintainOrderDto maintainOrder, PayChannelEnum payChannelEnum, BigDecimal price) ;

    /**
     * 支付完成，修改订单状态
     * @param payOrder
     * @param aliTradeNo
     */
    void finishPay(PayOrder payOrder, String aliTradeNo);

    /**
     * 根据商户支付订单号查询支付信息
     * @param out_trade_no
     * @return
     */
    PayOrder getByPayNo(String out_trade_no);
}
