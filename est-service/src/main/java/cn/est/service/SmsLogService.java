package cn.est.service;

/**
 * 短信功能业务层接口
 */
public interface SmsLogService {


    /**
     * 发送短信
     * @param phone
     * @param codeType
     * @param uId           发送人
     */
    void sendMessage(String phone, Integer codeType, Long uId);
}
