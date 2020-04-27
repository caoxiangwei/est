package cn.est.service;

import cn.est.pojo.Appointment;

/**
 * @Description 订单预约业务层接口
 * @Date 2019-08-19 14:39
 * @Author Liujx
 * Version 1.0
 **/
public interface AppointmentService {

    /**
     * 保存预约信息
     * @param appointment
     * @return
     */
    int save(Appointment appointment);
}
