package cn.est.pojo;
import cn.est.constants.Constants;
import cn.est.utils.DateUtils;
import cn.est.utils.MathUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/***
*   评估信息
*/
public class Evaluate implements Serializable {

    public Evaluate(){}

    public Evaluate(Long modelId, Long uId, BigDecimal price){
        Date currentDate = DateUtils.getCurrentDate(DateUtils.YYYY_MM_DD_HH_MM_SS);
        this.modelId = modelId;
        this.createdUserId = uId;
        this.price = price;
        // 如果计算出的订金金额小于最低订金费用则取最低订单费用
        price = MathUtils.formatDecimal(price.multiply(new BigDecimal(Constants.Order.Subscription.RATIO)), Constants.DECIMAL_DIGITS);
        if(price == null || price.compareTo(new BigDecimal(Constants.Order.Subscription.MINIMUM)) < 0){
            this.subscription = new BigDecimal(Constants.Order.Subscription.MINIMUM);
        }else{
            this.subscription = price;
        }
        this.isDelete = Constants.EST_NOT;
        this.creatdTime = currentDate;
    }

    private Long id;
    //型号id
    private Long modelId;
    //订金金额
    private BigDecimal subscription;
    //维修估价
    private BigDecimal price;
    //备注
    private String remark;
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
    public void setModelId (Long  modelId){
        this.modelId=modelId;
    }
    public  Long getModelId(){
        return this.modelId;
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
    public void setRemark (String  remark){
        this.remark=remark;
    }
    public  String getRemark(){
        return this.remark;
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
