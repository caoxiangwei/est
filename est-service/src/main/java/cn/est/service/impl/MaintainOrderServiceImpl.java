package cn.est.service.impl;

import cn.est.constants.Constants;
import cn.est.utils.DateUtils;
import cn.est.utils.MathUtils;
import cn.est.mapper.MaintainOrderMapper;
import cn.est.mapper.PayOrderMapper;
import cn.est.dto.EvaluateDto;
import cn.est.dto.MaintainOrderDto;
import cn.est.dto.MaintainOrderFlowDto;
import cn.est.dto.ModelDto;
import cn.est.constants.MaintainStatusEnum;
import cn.est.pojo.*;
import cn.est.req.AppointmentReq;
import cn.est.service.PayOrderService;
import cn.est.service.AppointmentService;
import cn.est.service.EvaluateService;
import cn.est.service.MaintainOrderService;
import cn.est.service.MaintainOrderFlowService;
import cn.est.service.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @Description 维修订单业务层实现
 * @Date 2019-08-19 13:37
 * @Author Liujx
 * Version 1.0
 **/
@Service
@Transactional
public class MaintainOrderServiceImpl implements MaintainOrderService {

    Logger log = LoggerFactory.getLogger(MaintainOrderServiceImpl.class);


    @Autowired
    private MaintainOrderMapper maintainOrderMapper;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private ModelService modelService;

    @Autowired
    private EvaluateService evaluateService;

    @Autowired
    private MaintainOrderFlowService maintainOrderFlowService;

    @Autowired
    private PayOrderService payOrderService;

    @Autowired
    private PayOrderMapper payOrderMapper;

    /**
     * 维修--下单
     * @param appointmentReq
     * @param eId
     * @param mId
     * @return
     * @throws ParseException
     */
    @Override
    public String submit(AppointmentReq appointmentReq, Long eId, Long mId) throws ParseException {
        String orderNo = null;
        // 1.保存预约信息
        Appointment appointment = new Appointment(appointmentReq);
        appointmentService.save(appointment);
        // 2.保存维修订单信息
        orderNo = save(mId, eId, appointmentReq.getUserId());
        return orderNo;
    }

    /**
     * 查询维修订单详情
     * @param orderNo
     * @return
     */
    @Override
    public MaintainOrderDto getByNo(String orderNo, Long uId) {
        MaintainOrderDto maintainOrder = getByNo(orderNo);
        if(maintainOrder != null){
            if(!maintainOrder.getUserId().equals(uId)){
                log.info("只能查询自己的订单orderUserId:{}，loginUserId:{}", maintainOrder.getId(), uId);
                return null;
            }
            // 1.格式化数据
            formatMaintainOrder(maintainOrder);
            // 2.商品信息
            ModelDto model = modelService.getById(maintainOrder.getModelId());
            maintainOrder.setModel(model);
            // 3.评估信息
            EvaluateDto evaluate = evaluateService.getById(maintainOrder.getEvaluateId());
            maintainOrder.setEvaluate(evaluate);
            // 4.交易流水信息
            List<MaintainOrderFlowDto> flowList = maintainOrderFlowService.getByOrderNo(maintainOrder.getOrderNo());
            maintainOrder.setFlowList(flowList);
        }
        return maintainOrder;
    }


    /**
     * 查询维修订单详情
     * @param orderNo
     * @return
     */
    @Override
    public MaintainOrderDto getByNo(String orderNo) {
        MaintainOrderDto maintainOrder = maintainOrderMapper.selectDtoByOrderNo(orderNo);
        return maintainOrder;
    }

    /**
     * 结束交易
     * @param aliTradeNo    支付宝订单号
     * @param payNo          商户支付订单号
     * @param totalAmount
     * @return
     */
    @Override
    public int finishPay(String aliTradeNo, String payNo, String totalAmount) {
        int succ = 0;
        PayOrder payOrder = payOrderMapper.selectByPayNo(payNo);
        if(payOrder == null){
            log.info("finishPay--->payNo:{},aliTradeNo:{}不存在", payNo, aliTradeNo);
            return succ;
        }
        String orderNo = payOrder.getOrderNo();
        MaintainOrder maintainOrder = maintainOrderMapper.selectByOrderNo(orderNo);
        Integer orderStatus = maintainOrder.getStatus();
        if(orderStatus == null || !orderStatus.equals(Constants.Order.MaintainStatus.APPOINT)){
            log.info("订单orderNo:{},aliTradeNo:{}支付状态为:{},不可支付", orderNo, aliTradeNo, maintainOrder.getStatus());
            return succ;
        }
        // 1.修改订单状态
        if(orderStatus.equals(Constants.Order.MaintainStatus.APPOINT)){
            maintainOrder.setStatus(Constants.Order.MaintainStatus.PAY_DEPOSIT);
        }
        maintainOrder.setPrice(new BigDecimal(totalAmount));
        Date currentDate = DateUtils.getCurrentDate(DateUtils.YYYY_MM_DD_HH_MM_SS);
        maintainOrder.setUpdatedTime(currentDate);
        succ = maintainOrderMapper.updateMaintainOrder(maintainOrder);
        // 2.修改支付订单状态
        payOrderService.finishPay(payOrder, aliTradeNo);
        // 3.保存订单流水信息
        maintainOrderFlowService.saveByMaintainOrder(maintainOrder);
        return succ;
    }

    /**
     * 格式化输出数据
     * @param maintainOrder
     */
    private void formatMaintainOrder(MaintainOrderDto maintainOrder) {
        /*// 封装支付渠道
        if (maintainOrder.getPayChannel() != null) {
            maintainOrder.setPayChannelDesc(PayChannelEnum.getByCode(maintainOrder.getPayChannel()).getMsg());
        }
        // 格式化支付时间
        Date payTime = maintainOrder.getPayTime();
        if(payTime != null){
            maintainOrder.setPayTimeStr(DateUtils.format(payTime, DateUtils.YYYY_MM_DD_HH_MM_SS));
        }*/
        // 封装订单状态说明
        maintainOrder.setStatusDesc(MaintainStatusEnum.getByCode(maintainOrder.getStatus()).getMsg());
    }

    /**
     * 保存维修订单
     * @param mId
     * @param eId
     * @param uId
     * @return
     * @throws Exception
     */
    private String save(Long mId, Long eId, Long uId) throws ParseException {
        MaintainOrder maintainOrder = new MaintainOrder(null, eId, mId, uId);
        EvaluateDto evaluate = evaluateService.getById(eId);
        // 订金金额
        maintainOrder.setSubscription(evaluate.getSubscription());
        maintainOrderMapper.insertMaintainOrder(maintainOrder);
        String orderNo = createOrderNo(maintainOrder.getId().toString());
        maintainOrder.setOrderNo(orderNo);
        maintainOrderMapper.updateMaintainOrder(maintainOrder);
        // 保存订单流水信息
        maintainOrderFlowService.saveByMaintainOrder(maintainOrder);
        return orderNo;
    }


    /**
     * 生成订单编号
     * 年月日时分秒毫秒 + （10位，不足补0）orderId
     * 2019082115563052070000994281
     * @param orderId
     * @return
     */
    private String createOrderNo(String orderId) throws ParseException {
        String orderNo = "";
        StringBuffer buffer = new StringBuffer(DateUtils.format(new Date(),DateUtils.YYYYMMDDHHMMSS));
        buffer.append(MathUtils.random());
        if(orderId.length() < 10){
            for (int i = 10 - orderId.length();i > 0; i--){
                buffer.append("0");
            }
        }
        orderNo = buffer.append(orderId).toString();
        // 根据生成的订单编号查询是否存在，如果存在则重新生成
        MaintainOrder maintainOrder = maintainOrderMapper.selectByOrderNo(orderNo);
        if(maintainOrder != null){
            return createOrderNo(orderId);
        }
        return orderNo;
    }

}
