package cn.est.pojo;
import cn.est.constants.Constants;
import cn.est.utils.DateUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
*   维修订单
*/
public class MaintainOrder implements Serializable {


    public MaintainOrder(){}

    public MaintainOrder(String orderNo, Long evaluateId, Long modelId, Long userId){
        Date currentDate = DateUtils.getCurrentDate(DateUtils.YYYY_MM_DD_HH_MM_SS);
        this.orderNo = orderNo;
        this.evaluateId = evaluateId;
        this.modelId = modelId;
        this.userId = userId;
        this.createdUserId = userId;
        this.status = Constants.Order.MaintainStatus.APPOINT;
        this.isComment = Constants.EST_NOT;
        this.creatdTime = currentDate;
        this.isDelete = Constants.EST_NOT;
    }

    // 主键id
    private Long id;
    //商品序列号
    private String serialNo;
    //订单编号
    private String orderNo;
    //用户id
    private Long userId;
    //型号id
    private Long modelId;
    //评估信息id
    private Long evaluateId;
    //订金金额
    private BigDecimal subscription;
    //实际交易金额
    private BigDecimal price;
    //订单状态（1:预约下单,2:支付定金,9:用户取消,10:完成）
    private Integer status;
    //是否评论
    private Integer isComment;
    //取消订单的原因
    private String cancelReason;
    //创建人
    private Long createdUserId;
    //修改人
    private Long updatedUserId;
    //创建时间
    private Date creatdTime;
    //修改时间
    private Date updatedTime;
    //是否删除(0:否,1:是)
    private Integer isDelete;
    //get set 方法
    public void setId (Long  id){
        this.id=id;
    }
    public  Long getId(){
        return this.id;
    }
    public void setSerialNo (String  serialNo){
        this.serialNo=serialNo;
    }
    public  String getSerialNo(){
        return this.serialNo;
    }
    public void setOrderNo (String  orderNo){
        this.orderNo=orderNo;
    }
    public  String getOrderNo(){
        return this.orderNo;
    }
    public void setUserId (Long  userId){
        this.userId=userId;
    }
    public  Long getUserId(){
        return this.userId;
    }
    public void setModelId (Long  modelId){
        this.modelId=modelId;
    }
    public  Long getModelId(){
        return this.modelId;
    }
    public void setEvaluateId (Long  evaluateId){
        this.evaluateId=evaluateId;
    }
    public  Long getEvaluateId(){
        return this.evaluateId;
    }
    public void setSubscription (BigDecimal  subscription){
        this.subscription=subscription;
    }
    public  BigDecimal getSubscription(){
        return this.subscription;
    }
    public void setPrice (BigDecimal  price){
        this.price=price;
    }
    public  BigDecimal getPrice(){
        return this.price;
    }
    public void setStatus (Integer  status){
        this.status=status;
    }
    public  Integer getStatus(){
        return this.status;
    }
    public void setIsComment (Integer  isComment){
        this.isComment=isComment;
    }
    public  Integer getIsComment(){
        return this.isComment;
    }
    public void setCancelReason (String  cancelReason){
        this.cancelReason=cancelReason;
    }
    public  String getCancelReason(){
        return this.cancelReason;
    }
    public void setCreatedUserId (Long  createdUserId){
        this.createdUserId=createdUserId;
    }
    public  Long getCreatedUserId(){
        return this.createdUserId;
    }
    public void setUpdatedUserId (Long  updatedUserId){
        this.updatedUserId=updatedUserId;
    }
    public  Long getUpdatedUserId(){
        return this.updatedUserId;
    }
    public void setCreatdTime (Date  creatdTime){
        this.creatdTime=creatdTime;
    }
    public  Date getCreatdTime(){
        return this.creatdTime;
    }
    public void setUpdatedTime (Date  updatedTime){
        this.updatedTime=updatedTime;
    }
    public  Date getUpdatedTime(){
        return this.updatedTime;
    }
    public void setIsDelete (Integer  isDelete){
        this.isDelete=isDelete;
    }
    public  Integer getIsDelete(){
        return this.isDelete;
    }
}
