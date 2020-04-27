package cn.est.pojo;
import java.io.Serializable;
import java.util.Date;
/***
*   商品分类
*/
public class Classify implements Serializable {
    //
    private Long id;
    //类型名称
    private String classifyName;
    //图标
    private String icon;
    //大图标
    private String bigIcon;
    //描述
    private String description;
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
    public void setClassifyName (String  classifyName){
        this.classifyName=classifyName;
    }
    public  String getClassifyName(){
        return this.classifyName;
    }
    public void setIcon (String  icon){
        this.icon=icon;
    }
    public  String getIcon(){
        return this.icon;
    }
    public void setBigIcon (String  bigIcon){
        this.bigIcon=bigIcon;
    }
    public  String getBigIcon(){
        return this.bigIcon;
    }
    public void setDescription (String  description){
        this.description=description;
    }
    public  String getDescription(){
        return this.description;
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
