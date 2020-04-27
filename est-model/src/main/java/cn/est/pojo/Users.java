package cn.est.pojo;
import cn.est.constants.Constants;
import cn.est.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;
/***
*   用户
*/
public class Users implements Serializable {

    public Users(){}

    public Users(String account, String openId, String userName){
        Date currentDate = DateUtils.getCurrentDate(DateUtils.YYYY_MM_DD_HH_MM_SS);
        this.account = account;
        this.openId = openId;
        this.userName = userName;
        this.isAuthentication = Constants.EST_NOT;
        this.setBindStatus(Constants.EST_NOT);
        this.isDelete = Constants.EST_NOT;
        this.creatdTime = currentDate;
    }
    //
    private Long id;
    //账号（手机号）
    private String account;
    //密码
    private String password;
    //盐
    private String salt;
    //昵称
    private String userName;
    //性别（0:女,1:男,2:保密）
    private Integer sex;
    //是否实名认证（0:否,1:是）
    private Integer isAuthentication;
    //真是姓名
    private String name;
    //身份证号
    private String cardNo;
    //头像
    private String faceUrl;
    //微信openId
    private String openId;
    //微信绑定状态（0:未绑定,1:已绑定,2:已解绑）
    private Integer bindStatus;
    //微信绑定时间
    private Date bindTime;
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
    public void setAccount (String  account){
        this.account=account;
    }
    public  String getAccount(){
        return this.account;
    }
    public void setPassword (String  password){
        this.password=password;
    }
    public  String getPassword(){
        return this.password;
    }
    public void setSalt (String  salt){
        this.salt=salt;
    }
    public  String getSalt(){
        return this.salt;
    }
    public void setUserName (String  userName){
        this.userName=userName;
    }
    public  String getUserName(){
        return this.userName;
    }
    public void setSex (Integer  sex){
        this.sex=sex;
    }
    public  Integer getSex(){
        return this.sex;
    }
    public void setIsAuthentication (Integer  isAuthentication){
        this.isAuthentication=isAuthentication;
    }
    public  Integer getIsAuthentication(){
        return this.isAuthentication;
    }
    public void setName (String  name){
        this.name=name;
    }
    public  String getName(){
        return this.name;
    }
    public void setCardNo (String  cardNo){
        this.cardNo=cardNo;
    }
    public  String getCardNo(){
        return this.cardNo;
    }
    public void setFaceUrl (String  faceUrl){
        this.faceUrl=faceUrl;
    }
    public  String getFaceUrl(){
        return this.faceUrl;
    }
    public void setOpenId (String  openId){
        this.openId=openId;
    }
    public  String getOpenId(){
        return this.openId;
    }
    public void setBindStatus (Integer  bindStatus){
        this.bindStatus=bindStatus;
    }
    public  Integer getBindStatus(){
        return this.bindStatus;
    }
    public void setBindTime (Date  bindTime){
        this.bindTime=bindTime;
    }
    public  Date getBindTime(){
        return this.bindTime;
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
