package cn.est.service.impl;

import cn.est.mapper.AppointmentMapper;
import cn.est.pojo.Appointment;
import cn.est.service.AppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description 订单预约业务层实现
 * @Date 2019-08-19 14:40
 * @Author Liujx
 * Version 1.0
 **/
@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    Logger log = LoggerFactory.getLogger(AppointmentServiceImpl.class);


    @Autowired
    private AppointmentMapper appointmentMapper;


    /**
     * 保存预约信息
     * @param appointment
     * @return
     */
    @Override
    public int save(Appointment appointment) {
        int succ = 0;
        succ = appointmentMapper.insertAppointment(appointment);
        return succ;
    }
}
