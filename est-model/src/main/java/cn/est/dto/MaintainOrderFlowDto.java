package cn.est.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description 订单流水信息包装类
 * @Date 2019-08-20 12:31
 * @Author Liujx
 * Version 1.0
 **/
@ApiModel(value = "MaintainOrderFlowDto", description = "订单流水信息包装类")
public class MaintainOrderFlowDto {

    @ApiModelProperty(value = "订单流水id")
    private Long id;
    //订单编号
    @ApiModelProperty(value = "订单编号")
    private String orderNo;
    //订单状态（1:预约下单,2:支付定金,9:用户取消,10:完成）
    @ApiModelProperty(value = "订单状态（1:预约下单,2:支付定金,9:用户取消,10:完成）")
    private Integer orderStatus;
    //订单状态说明
    @ApiModelProperty(value = "订单状态说明")
    private String orderStatusDesc;
    //操作人id
    @ApiModelProperty(value = "操作人id")
    private Long operationUId;
    //操作人姓名
    @ApiModelProperty(value = "操作人姓名")
    private String operationName;
    // 操作时间
    @ApiModelProperty(value = "操作时间")
    private String operationTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatusDesc() {
        return orderStatusDesc;
    }

    public void setOrderStatusDesc(String orderStatusDesc) {
        this.orderStatusDesc = orderStatusDesc;
    }

    public Long getOperationUId() {
        return operationUId;
    }

    public void setOperationUId(Long operationUId) {
        this.operationUId = operationUId;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }
}
