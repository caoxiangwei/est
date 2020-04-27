package cn.est.pojo;
import cn.est.constants.Constants;
import cn.est.utils.DateUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/***
*   订单支付信息
*/
public class PayOrder implements Serializable {

    public PayOrder(){}

    public PayOrder(String orderNo,String payNo, Long userId, BigDecimal price, Integer payType, Integer payChannel){

        Date currentTime = DateUtils.getCurrentDate(DateUtils.YYYY_MM_DD_HH_MM_SS);
        this.orderNo = orderNo;
        this.payNo = payNo;
        this.payType = payType;
        this.payChannel = payChannel;
        this.price = price;
        this.status = Constants.Order.PayStaus.UN_PAY;
        this.userId = userId;
        this.createdUserId = userId;
        this.creatdTime = currentTime;
        this.isDelete = Constants.EST_NOT;
    }

    //
    private Long id;
    //订单编号
    private String orderNo;
    //支付订单号
    private String payNo;
    //阿里交易订单号
    private String aliTradeNo;
    //用户id
    private Long userId;
    //支付金额
    private BigDecimal price;
    //支付类型（1:定金,2:尾款）
    private Integer payType;
    //支付状态（1:未支付,2:支付成功,3:支付失败）
    private Integer status;
    //支付途径（1:现金,2:刷卡,3:支付宝,4:微信）
    private Integer payChannel;
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
    public void setOrderNo (String  orderNo){
        this.orderNo=orderNo;
    }
    public  String getOrderNo(){
        return this.orderNo;
    }
    public void setPayNo (String  payNo){
        this.payNo=payNo;
    }
    public  String getPayNo(){
        return this.payNo;
    }
    public void setAliTradeNo (String  aliTradeNo){
        this.aliTradeNo=aliTradeNo;
    }
    public  String getAliTradeNo(){
        return this.aliTradeNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setPrice (BigDecimal  price){
        this.price=price;
    }
    public  BigDecimal getPrice(){
        return this.price;
    }
    public void setPayType (Integer  payType){
        this.payType=payType;
    }
    public  Integer getPayType(){
        return this.payType;
    }
    public void setStatus (Integer  status){
        this.status=status;
    }
    public  Integer getStatus(){
        return this.status;
    }
    public void setPayChannel (Integer  payChannel){
        this.payChannel=payChannel;
    }
    public  Integer getPayChannel(){
        return this.payChannel;
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
