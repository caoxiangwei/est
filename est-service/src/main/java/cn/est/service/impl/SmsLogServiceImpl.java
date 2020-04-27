package cn.est.service.impl;

import cn.est.constants.Constants;
import cn.est.utils.reids.RedisKeyUtils;
import cn.est.utils.reids.RedisUtils;
import cn.est.utils.sms.SendSms;
import cn.est.mapper.SmsLogMapper;
import cn.est.pojo.SmsLog;
import cn.est.service.SmsLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 短信功能业务层实现
 */
@Service
@Transactional
public class SmsLogServiceImpl implements SmsLogService {

    Logger log = LoggerFactory.getLogger(SmsLogServiceImpl.class);

    @Autowired
    private SendSms sendSms;
    @Autowired
    private SmsLogMapper smsLogMapper;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 异步发送短信
     * @Async 注解表示这是一个异步否方法
     * @param phone
     * @param codeType
     * @param uId           发送人
     */
    @Async
    @Override
    public void sendMessage(String phone, Integer codeType, Long uId) {
        Integer status = Constants.Sms.STATUS_SEND_FAILED;
        // 1.调用短信发送接口，发送短信
        String sendCode = "0000";
        String msgResult = "OK";
//        String sendCode = MathUtils.random();
        log.info("发送短信phone:{},验证码：{}" , phone, sendCode);
//        String msgResult = sendSms.send(phone, sendCode);
        // 2.保存短信发送信息
        if(msgResult != null && msgResult.equals("OK")){
            status = Constants.Sms.STATUS_SEND_SUCCESS;
            // 3.缓存验证码，并保存有效性
            String key = RedisKeyUtils.formatKeyWithPrefix(Constants.Redis.PREFIX_SMS, codeType.toString(), phone);
            // 把验证码放在redis中
            redisUtils.putValue(key, sendCode , Constants.Duration.MINUTE_INT);
        }
        SmsLog smsLog = new SmsLog(phone, codeType, sendCode, msgResult, status, uId);
        smsLog.setFailInfo(msgResult);
        smsLogMapper.insertSmsLog(smsLog);
    }
}
