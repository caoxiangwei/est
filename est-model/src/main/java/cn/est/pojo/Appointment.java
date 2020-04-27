package cn.est.pojo;

import cn.est.constants.Constants;
import cn.est.req.AppointmentReq;
import cn.est.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;
/***
 *   预约
 */
public class Appointment implements Serializable {

    public Appointment(){}

    public Appointment(AppointmentReq req){
        Date currentDate = DateUtils.getCurrentDate(DateUtils.YYYY_MM_DD_HH_MM_SS);
        this.phone = req.getPhone();
        this.appintDate = req.getAppintDate();
        this.temporalInterval = req.getTemporalInterval();
        this.adress = req.getAdress();
        this.userId = req.getUserId();
        this.createdUserId = req.getUserId();
        this.status = Constants.Order.AppointStatus.APPOINT;
        this.isDelete = Constants.EST_NOT;
        this.creatdTime = currentDate;
    }

    //
    private Long id;
    //预约编号
    private String appointCode;
    //用户id
    private Long userId;
    //预约手机号
    private String phone;
    //预约日期
    private Date appintDate;
    //预约时间（1:上午,2:中午,3:下午,4:晚上）
    private Integer temporalInterval;
    //预约上门维修地址
    private String adress;
    //状态((1:用户预约,2:完成)
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
    public void setAppointCode (String  appointCode){
        this.appointCode=appointCode;
    }
    public  String getAppointCode(){
        return this.appointCode;
    }
    public void setUserId (Long  userId){
        this.userId=userId;
    }
    public  Long getUserId(){
        return this.userId;
    }
    public void setPhone (String  phone){
        this.phone=phone;
    }
    public  String getPhone(){
        return this.phone;
    }
    public void setAppintDate (Date  appintDate){
        this.appintDate=appintDate;
    }
    public  Date getAppintDate(){
        return this.appintDate;
    }
    public void setTemporalInterval (Integer  temporalInterval){
        this.temporalInterval=temporalInterval;
    }
    public  Integer getTemporalInterval(){
        return this.temporalInterval;
    }
    public void setAdress (String  adress){
        this.adress=adress;
    }
    public  String getAdress(){
        return this.adress;
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
