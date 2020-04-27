package cn.est.controller;

import cn.est.constants.Constants;
import cn.est.dto.Result;
import cn.est.constants.ResultEnum;
import cn.est.utils.ResultUtils;
import cn.est.utils.DateUtils;
import cn.est.utils.StringUtil;
import cn.est.utils.reids.RedisKeyUtils;
import cn.est.utils.reids.RedisUtils;
import cn.est.dto.EvaluateDto;
import cn.est.dto.MaintainOrderDto;
import cn.est.dto.UsersDto;
import cn.est.req.AppointmentReq;
import cn.est.service.EvaluateService;
import cn.est.service.MaintainOrderService;
import cn.est.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @Description 维修订单控制器
 * @Date 2019-08-19 13:33
 * @Author Liujx
 * Version 1.0
 **/
@Api(description = "维修订单控制器")
@RestController
@RequestMapping("/api/order/maintain")
public class MaintainOrderController {

    Logger log = LoggerFactory.getLogger(MaintainOrderController.class);

    @Autowired
    private MaintainOrderService maintainOrderService;

    @Autowired
    private UserService userService;

    @Autowired
    private EvaluateService evaluateService;

    @Autowired
    private RedisUtils redisUtils;


    /**
     * 维修--下单
     * @param appointment
     * @param evaluateId
     * @return
     */
    @ApiOperation(value = "维修--下单", produces = "application/json", notes = "提交维修订单")
    @ApiImplicitParams({@ApiImplicitParam(name = "evaluateId", value = "评估信息id", required = true, dataType = "Long")
            ,@ApiImplicitParam(paramType = "header",name = "token", value = "用户token", required = true, dataType = "String")})
    @PostMapping("/submit")
    public  Result<Map<String,Object>> submit(AppointmentReq appointment, Long evaluateId, HttpServletRequest request) throws ParseException {
        log.info("保存维修订单数据appointment：{}", appointment);
        Result<Map<String,Object>> result = null;
        String token = request.getHeader("token");
        UsersDto user = userService.getLoginUser(token);
        // 1.校验预约日期，不能早于今天，最晚4天之内
        if (!checkAppointDate(appointment.getAppintDate())) {
            log.info("预约日期不正确:{}", appointment.getAppintDate());
            return ResultUtils.returnFail("预约日期不正确", ResultEnum.FAIL_PARAM.getCode());
        }
        // 2.校验预约时间区间，上午、中午、下午、晚上
        Integer temporalInterval = appointment.getTemporalInterval();
        if (!checkTemporalInterval(appointment.getTemporalInterval())) {
            log.info("预约时间段不正确:{}", appointment.getTemporalInterval());
            return ResultUtils.returnFail("预约时间段不正确", ResultEnum.FAIL_PARAM.getCode());
        }
        // 3.手机号校验
        if (!checkPhone(appointment.getPhone())) {
            log.info("手机号码错误phone:{}", appointment.getPhone());
            return ResultUtils.returnFail("手机号码错误", ResultEnum.FAIL_PARAM.getCode());
        }
        // 4.短信验证码校验
        if (!checkSmsCode(appointment.getPhone(), Constants.Sms.TYPE_SUBMIT_CHECK, appointment.getSms())) {
            log.info("短信验证码错误sms:{}", appointment.getSms());
            return ResultUtils.returnFail("短信验证码错误", ResultEnum.FAIL_PARAM.getCode());
        }
        // 5.地址长度校验(100字以内)
        if (appointment.getAdress().length() > 100) {
            log.info("预约地址长度过长address:{}", appointment.getAdress());
            return ResultUtils.returnFail("预约地址长度过长", ResultEnum.FAIL_PARAM.getCode());
        }
        // 6.校验评估信息
        EvaluateDto evaluateDto = evaluateService.getById(evaluateId);
        if(evaluateDto == null){
            log.info("根据评估id查询不到评估信息evaluateId:{}", evaluateId);
            return ResultUtils.returnResult(ResultEnum.FAIL_HAVE_NOT_EXIST);
        }
        appointment.setUserId(user.getId());
        String orderNo = maintainOrderService.submit(appointment, evaluateId, evaluateDto.getModelId());
        Map<String,Object> data = StringUtil.createSimpleMap("orderNo", orderNo);
        result = ResultUtils.returnDataSuccess(data);
        return result;
    }

    /**
     * 查询维修订单详情
     * @param orderNo
     * @return
     */
    @ApiOperation(value = "查询维修订单详情", produces = "application/json", notes = "根据id查询维修订单详情信息")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "token", value = "用户token", required = true, dataType = "String")
            ,@ApiImplicitParam(paramType = "path",name = "orderNo", value = "维修订单编号", required = true, dataType = "String")})
    @GetMapping("/detail/{orderNo}")
    public Result<MaintainOrderDto> detail(@PathVariable String orderNo, HttpServletRequest request) {
        log.info("根据维修订单编号查询订单信息orderNo:{}" ,orderNo);
        Result<MaintainOrderDto> result = null;
        String token = request.getHeader("token");
        UsersDto user = userService.getLoginUser(token);
        MaintainOrderDto data = maintainOrderService.getByNo(orderNo, user.getId());
        // 只能查询自己的订单
        if(data == null || !data.getUserId().equals(user.getId())){
            log.info("订单信息不存在orderNo:{}", orderNo);
            return ResultUtils.returnResult(ResultEnum.FAIL_HAVE_NOT_EXIST);
        }
        result = ResultUtils.returnDataSuccess(data);
        return result;
    }


    /**
     * 校验预约时间段
     * @param temporalInterval
     * @return
     */
    private boolean checkTemporalInterval(Integer temporalInterval) {
        boolean flag = false;
        if (temporalInterval.equals(Constants.Order.TemporalInterval.AM)
                || temporalInterval.equals(Constants.Order.TemporalInterval.NOON)
                || temporalInterval.equals(Constants.Order.TemporalInterval.PM)
                || temporalInterval.equals(Constants.Order.TemporalInterval.NIGHT)) {
            flag = true;
        }
        return flag;
    }

    /**
     * 校验预约时间
     * @param appintDate
     * @return
     */
    private boolean checkAppointDate(Date appintDate) {
        boolean flag = false;
        Date currentDate = DateUtils.getCurrentDate(DateUtils.YYYY_MM_DD);
        if(appintDate.compareTo(currentDate) >= 0 && appintDate.compareTo(DateUtils.addDate(currentDate, 4)) <= 1){
            flag = true;
        }
        return flag;
    }

    /**
     * 验证手机号
     * @param phone
     */
    private boolean checkPhone(String phone) {
        boolean flag = false;
        if (!org.apache.commons.lang3.StringUtils.isBlank(phone)) {
            if (phone.length() == 11 && phone.startsWith("1")) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 验证验证码
     *
     * @param phone
     * @param codeType
     */
    private boolean checkSmsCode(String phone, Integer codeType, String sms) {
        String value = getVerifyCodeFromRedis(phone,codeType);
        return sms.equals(value);
    }

    /**
     * 从reids中获取验证码
     * @param phone
     * @param codeType
     */
    public String getVerifyCodeFromRedis(String phone ,Integer codeType) {
        String key = RedisKeyUtils.formatKeyWithPrefix(Constants.Redis.PREFIX_SMS, codeType.toString(), phone);
        return redisUtils.getValue(key);
    }

    /**
     * 格式化入参时间
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
