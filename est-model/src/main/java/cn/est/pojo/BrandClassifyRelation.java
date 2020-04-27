package cn.est.pojo;
import java.io.Serializable;
import java.util.Date;
/***
*   品牌-类型-关系表
*/
public class BrandClassifyRelation implements Serializable {
    //
    private Long id;
    //品牌id
    private Long brandId;
    //类型id
    private Long classifyId;
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
    public void setBrandId (Long  brandId){
        this.brandId=brandId;
    }
    public  Long getBrandId(){
        return this.brandId;
    }
    public void setClassifyId (Long  classifyId){
        this.classifyId=classifyId;
    }
    public  Long getClassifyId(){
        return this.classifyId;
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
