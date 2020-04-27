package cn.est.service;

import cn.est.dto.MaintainOrderDto;
import cn.est.req.AppointmentReq;

import java.text.ParseException;

/**
 * @Description 维修订单业务层接口
 * @Date 2019-08-19 13:37
 * @Author Liujx
 * Version 1.0
 **/
public interface MaintainOrderService {

    /**
     * 维修--下单
     * @param appointmentReq
     */
    String submit(AppointmentReq appointmentReq, Long eId, Long mId) throws ParseException;

    /**
     * 查询维修订单详情
     * @param orderNo
     * @param uId
     * @return
     */
    MaintainOrderDto getByNo(String orderNo, Long uId);

    /**
     * 查询维修订单详情
     * @param orderNo
     * @return
     */
    public MaintainOrderDto getByNo(String orderNo);

    /**
     * 结束交易
     * @param aliTradeNo    支付宝订单号
     * @param payNo          商户支付订单号
     * @param totalAmount
     * @return
     */
    int finishPay(String aliTradeNo, String payNo, String totalAmount);
}
