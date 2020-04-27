package cn.est.pojo;
import java.io.Serializable;
import java.util.Date;
/***
*   门店
*/
public class Store implements Serializable {
    //
    private Long id;
    //门店名称
    private String storeName;
    //联系电话
    private String phone;
    //地址
    private String adress;
    //经度
    private Double longitude;
    //纬度
    private Double latitude;
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
    public void setStoreName (String  storeName){
        this.storeName=storeName;
    }
    public  String getStoreName(){
        return this.storeName;
    }
    public void setPhone (String  phone){
        this.phone=phone;
    }
    public  String getPhone(){
        return this.phone;
    }
    public void setAdress (String  adress){
        this.adress=adress;
    }
    public  String getAdress(){
        return this.adress;
    }
    public void setLongitude (Double  longitude){
        this.longitude=longitude;
    }
    public  Double getLongitude(){
        return this.longitude;
    }
    public void setLatitude (Double  latitude){
        this.latitude=latitude;
    }
    public  Double getLatitude(){
        return this.latitude;
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
