package cn.est.pojo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/***
*   故障
*/
public class Malfunction implements Serializable {
    //
    private Long id;
    //商品型号id
    private Long modelId;
    //问题状况名称
    private String title;
    //是否有提示0:否,1:是)
    private Integer isHint;
    //提示信息标题
    private String hintTitle;
    //提示信息
    private String hintInfo;
    //提示图片
    private String hintImg;
    //维修最高价
    private BigDecimal maintainTopPrice;
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
    public void setTitle (String  title){
        this.title=title;
    }
    public  String getTitle(){
        return this.title;
    }
    public void setIsHint (Integer  isHint){
        this.isHint=isHint;
    }
    public  Integer getIsHint(){
        return this.isHint;
    }
    public void setHintTitle (String  hintTitle){
        this.hintTitle=hintTitle;
    }
    public  String getHintTitle(){
        return this.hintTitle;
    }
    public void setHintInfo (String  hintInfo){
        this.hintInfo=hintInfo;
    }
    public  String getHintInfo(){
        return this.hintInfo;
    }
    public void setHintImg (String  hintImg){
        this.hintImg=hintImg;
    }
    public  String getHintImg(){
        return this.hintImg;
    }
    public void setMaintainTopPrice (BigDecimal  maintainTopPrice){
        this.maintainTopPrice=maintainTopPrice;
    }
    public  BigDecimal getMaintainTopPrice(){
        return this.maintainTopPrice;
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
