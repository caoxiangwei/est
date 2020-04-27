package cn.est.pojo;
import java.io.Serializable;
import java.util.Date;
/***
 *   品牌
 */
public class Brand implements Serializable {
    //
    private Long id;
    //品牌名称
    private String brandName;
    //品牌logo
    private String logo;
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
    public void setBrandName (String  brandName){
        this.brandName=brandName;
    }
    public  String getBrandName(){
        return this.brandName;
    }
    public void setLogo (String  logo){
        this.logo=logo;
    }
    public  String getLogo(){
        return this.logo;
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
