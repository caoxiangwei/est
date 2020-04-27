package cn.est.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description 维修订单包装类
 * @Date 2019-08-20 09:34
 * @Author Liujx
 * Version 1.0
 **/
@ApiModel(value = "MaintainOrderDto", description = "维修订单包装类")
public class MaintainOrderDto {

    // 主键id
    @ApiModelProperty("维修订单id")
    private Long id;
    //商品序列号
    @ApiModelProperty("商品序列号")
    private String serialNo;
    //订单编号
    @ApiModelProperty("订单编号")
    private String orderNo;
    //用户id
    @ApiModelProperty("用户id")
    private Long userId;
    //型号id
    @ApiModelProperty("商品型号id")
    private Long modelId;
    //评估信息id
    @ApiModelProperty("评估信息id")
    private Long evaluateId;
    //订金金额
    @ApiModelProperty("订金金额")
    private BigDecimal subscription;
    //实际交易金额
    @ApiModelProperty("实际交易金额")
    private BigDecimal price;
    //订单状态（订单状态（1:预约下单,2:支付定金,9:用户取消,10:完成））
    @ApiModelProperty("订单状态（1:预约下单,2:支付定金,9:用户取消,10:完成）")
    private Integer status;
    // 支付途径说明
    @ApiModelProperty("支付途径说明")
    private String payChannelDesc;
    // 订单状态说明
    @ApiModelProperty("订单状态说明")
    private String statusDesc;
    // 支付时间
    @ApiModelProperty("支付时间")
    private String payTimeStr;
    // 商品型号
    @ApiModelProperty("商品型号")
    private ModelDto model;
    // 评估信息
    @ApiModelProperty("评估信息")
    private EvaluateDto evaluate;
    // 维修订单流水信息
    @ApiModelProperty("维修订单流水信息")
    private List<MaintainOrderFlowDto> flowList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public Long getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(Long evaluateId) {
        this.evaluateId = evaluateId;
    }

    public BigDecimal getSubscription() {
        return subscription;
    }

    public void setSubscription(BigDecimal subscription) {
        this.subscription = subscription;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPayChannelDesc() {
        return payChannelDesc;
    }

    public void setPayChannelDesc(String payChannelDesc) {
        this.payChannelDesc = payChannelDesc;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getPayTimeStr() {
        return payTimeStr;
    }

    public void setPayTimeStr(String payTimeStr) {
        this.payTimeStr = payTimeStr;
    }

    public ModelDto getModel() {
        return model;
    }

    public void setModel(ModelDto model) {
        this.model = model;
    }

    public EvaluateDto getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(EvaluateDto evaluate) {
        this.evaluate = evaluate;
    }

    public List<MaintainOrderFlowDto> getFlowList() {
        return flowList;
    }

    public void setFlowList(List<MaintainOrderFlowDto> flowList) {
        this.flowList = flowList;
    }
}
