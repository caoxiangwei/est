package cn.est.controller;

import cn.est.constants.Constants;
import cn.est.dto.Result;
import cn.est.constants.ResultEnum;
import cn.est.utils.ResultUtils;
import cn.est.utils.reids.RedisKeyUtils;
import cn.est.utils.reids.RedisUtils;
import cn.est.service.SmsLogService;
import cn.est.dto.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 短信控制器
 */
@Api(description = "短信控制器")
@RestController
@RequestMapping("/api/sms")
public class SmsController {

    Logger log = LoggerFactory.getLogger(SmsController.class);

    @Autowired
    private SmsLogService smsLogService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 发送短信
     * @param phone
     * @param codeType  验证码类型（0:注册或登录验证码,1:修改密码验证码,2:通知,3:下单验证码）
     * @return
     */
    @ApiOperation(value = "发送短信", produces = "application/json", notes = "发送短信")
    @ApiImplicitParams({@ApiImplicitParam(name = "phone", value = "手机号码", required = true, dataType = "String")
            ,@ApiImplicitParam(name = "codeType", value = "验证码类型（0:注册或登录验证码,1:修改密码验证码,2:通知,3:下单验证码）", required = true, dataType = "int")})
    @PostMapping("/verifyCode")
    public Result verifyCode(String phone, Integer codeType) throws BaseException {
        log.info("发送短信phone：{},短信类型codeType:{}", phone, codeType);
        Result result = null;
        // 1.判断手机号是否正确
        if(!checkPhone(phone)){
            log.info("手机号码错误phone:{}", phone);
            return ResultUtils.returnFail("手机号码错误", ResultEnum.FAIL_PARAM.getCode());
        }
        // 2.判断短信类型是否正确
        if(!checkCodeType(codeType)){
            log.info("短信类型错误codeType:{}", codeType);
            return ResultUtils.returnFail("短信类型错误", ResultEnum.FAIL_PARAM.getCode());
        }
        // 3.验证码发送频率
        if(!checkSmsCode(phone, codeType)){
            log.info("验证码发送频率过高");
            return ResultUtils.returnFail("请勿重复发送。");
        }
        smsLogService.sendMessage(phone, codeType, null);
        result = ResultUtils.returnSuccess();
        return result;
    }

    /**
     * 验证验证码
     * @param phone
     * @param codeType
     */
    private boolean checkSmsCode(String phone, Integer codeType) {
        boolean flag = false;
        String key = RedisKeyUtils.formatKeyWithPrefix(Constants.Redis.PREFIX_SMS, codeType.toString(), phone);
        String smsCode = redisUtils.getValue(key);
        // 判断验证码是否有效期内发送，如果不存在说明已经过了有效期，可以发送
        if(StringUtils.isBlank(smsCode)){
            flag = true;
        }
        return flag;
    }


    /**
     * 验证手机号
     * @param phone
     */
    private boolean checkPhone(String phone) throws BaseException {
        boolean flag = false;
        if(!StringUtils.isBlank(phone)){
            flag = phone.matches(Constants.PHONE_REGSTR);
        }
        return flag;
    }

    /**
     * 验证短信类型
     * @param codeType
     */
    private boolean checkCodeType(Integer codeType) {
        boolean flag = false;
        if(codeType != null){
            // 如果是不已定义的短信类型，不允许发送
            if(codeType.equals(Constants.Sms.TYPE_REGIST_OR_LOGIN) || codeType.equals(Constants.Sms.TYPE_UP_PASS)
                    || codeType.equals(Constants.Sms.TYPE_NOTICE) || codeType.equals(Constants.Sms.TYPE_SUBMIT_CHECK)){
                flag = true;
            }
        }
        return flag;
    }


}
