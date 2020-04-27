package cn.est.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;

import java.util.Date;

/**
 * @Description 预约信息入参包装类
 * @Date 2019-09-03 16:51
 * @Author Liujx
 * Version 1.0
 **/
@ApiModel(value = "AppointmentReq", description = "预约信息入参包装类")
public class AppointmentReq {

    //预约编号
    @ApiParam(hidden = true)
    private String appointCode;
    //用户id
    @ApiParam(hidden = true)
    private Long userId;
    //预约手机号
    @ApiParam(value = "预约手机号", required = true)
    private String phone;
    //短信验证码
    @ApiParam(value = "短信验证码", required = true)
    private String sms;
    //预约日期
    @ApiParam(value = "预约日期", required = true)
    private Date appintDate;
    //预约时间段（1:上午,2:中午,3:下午,4:晚上）
    @ApiParam(value = "预约时间段（1:上午,2:中午,3:下午,4:晚上）", required = true)
    private Integer temporalInterval;
    //预约上门维修地址
    @ApiParam(value = "预约上门维修地址(100字以内)", required = true)
    private String adress;

    public String getAppointCode() {
        return appointCode;
    }

    public void setAppointCode(String appointCode) {
        this.appointCode = appointCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getAppintDate() {
        return appintDate;
    }

    public void setAppintDate(Date appintDate) {
        this.appintDate = appintDate;
    }

    public Integer getTemporalInterval() {
        return temporalInterval;
    }

    public void setTemporalInterval(Integer temporalInterval) {
        this.temporalInterval = temporalInterval;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }
}
