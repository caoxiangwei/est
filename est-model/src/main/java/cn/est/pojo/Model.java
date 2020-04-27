package cn.est.pojo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
*   商品型号
*/
public class Model implements Serializable {
    //
    private Long id;
    //型号名称
    private String modelName;
    //品牌id
    private Long brandId;
    //类型id
    private Long classifyId;
    //当前行情价格
    private BigDecimal exchangePrice;
    //最高价
    private BigDecimal topPrice;
    //封面图片
    private String faceImg;
    //内容图片
    private String contentImg;
    //描述
    private String description;
    //状态（0:下架,1:上架）
    private Integer status;
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
    public void setModelName (String  modelName){
        this.modelName=modelName;
    }
    public  String getModelName(){
        return this.modelName;
    }
    public void setBrandId (Long  brandId){
        this.brandId=brandId;
    }
    public  Long getBrandId(){
        return this.brandId;
    }

    public Long getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Long classifyId) {
        this.classifyId = classifyId;
    }

    public void setExchangePrice (BigDecimal  exchangePrice){
        this.exchangePrice=exchangePrice;
    }
    public  BigDecimal getExchangePrice(){
        return this.exchangePrice;
    }
    public void setTopPrice (BigDecimal  topPrice){
        this.topPrice=topPrice;
    }
    public  BigDecimal getTopPrice(){
        return this.topPrice;
    }
    public void setFaceImg (String  faceImg){
        this.faceImg=faceImg;
    }
    public  String getFaceImg(){
        return this.faceImg;
    }
    public void setContentImg (String  contentImg){
        this.contentImg=contentImg;
    }
    public  String getContentImg(){
        return this.contentImg;
    }
    public void setDescription (String  description){
        this.description=description;
    }
    public  String getDescription(){
        return this.description;
    }
    public void setStatus (Integer  status){
        this.status=status;
    }
    public  Integer getStatus(){
        return this.status;
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
