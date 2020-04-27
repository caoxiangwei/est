package cn.est.controller;

import cn.est.constants.Constants;
import cn.est.dto.Result;
import cn.est.constants.ResultEnum;
import cn.est.utils.ResultUtils;
import cn.est.utils.UrlUtils;
import cn.est.utils.StringUtil;
import cn.est.utils.reids.RedisKeyUtils;
import cn.est.utils.reids.RedisUtils;
import cn.est.dto.UsersDto;
import cn.est.service.UserService;
import cn.est.config.WechatConfig;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


/**
 * 用户功能控制器
 */
@Api(description = "用户功能控制器")
@RestController
@RequestMapping("/api/user")
public class UserController {

    Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private WechatConfig wechatConfig;

    /**
     * 短信登录、注册
     *
     * @param phone
     * @param sms
     * @return
     */
    @ApiOperation(value = "短信验证码登录、注册", produces = "application/json", notes = "使用短信验证码进行快速登陆或注册。")
    @ApiImplicitParams({@ApiImplicitParam(name = "phone", value = "手机号码", required = true, dataType = "String")
            ,@ApiImplicitParam(name = "sms", value = "验证码", required = true, dataType = "String")})
    @PostMapping("/login/sms")
    public Result<Map<String,Object>> login(String phone, String sms) {
        log.info("短信登录,phone={}，smsCode:{}", phone, sms);
        Result<Map<String,Object>> result = null;
        // 1.判断手机号是否正确
        if(!checkPhone(phone)){
            log.info("手机号码错误phone:{}", phone);
            return ResultUtils.returnFail("手机号码错误", ResultEnum.FAIL_PARAM.getCode());
        }
        // 2.校验验证码
        if (!checkSmsCode(phone, Constants.Sms.TYPE_REGIST_OR_LOGIN, sms)) {
            log.info("验证码错误phone:{}，smsCode:{}", phone, sms);
            return ResultUtils.returnResult(ResultEnum.FAIL_VERIFY);
        }
        // login
        UsersDto usersVo = userService.loginByPhone(phone);
        if (usersVo != null) {
            result = ResultUtils.returnDataSuccess(StringUtil.createSimpleMap("token", usersVo.getToken()));
        }else {
            result = ResultUtils.returnResult(ResultEnum.FAIL_ACCOUNT_NOT_EXIST);
        }
        return result;
    }

    /**
     * 微信登录——第一步：获取code
     * @param response
     */
    @ApiOperation(value = "微信登陆、注册", produces = "application/json", notes = "使用微信扫码进行快速登陆或注册。")
    @GetMapping(value = "/login/wechat")
    public void wechatLogin(HttpServletResponse response){
        try {
            StringBuilder openBuilder = new StringBuilder(wechatConfig.getOpen_url())
                    .append("?").append("appid=").append(wechatConfig.getAppid())
                    .append("&").append("redirect_uri=").append(wechatConfig.getRedirect_uri())
                    .append("&").append("response_type=").append(wechatConfig.getGrant_type())
                    .append("&").append("scope=").append(wechatConfig.getScope())
                    .append("&").append("state=").append(wechatConfig.getState());
            response.sendRedirect(openBuilder.toString());
        } catch (Exception e) {
            log.error("唤起微信登录页面失败:{}", e);
        }

    }
    /**
     * 微信登录——第二步：通过code换取access_token
     * @param code
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping(value = "/wechat/callback")
    public void wechatCallback(@RequestParam String code, HttpServletRequest request, HttpServletResponse response) throws IOException{
        StringBuilder accessTokenBuilder = new StringBuilder(wechatConfig.getAccess_token_url())
            .append("?").append("appid=").append(wechatConfig.getAppid())
            .append("&").append("secret=").append(wechatConfig.getSecret())
            .append("&").append("code=").append(code)
            .append("&").append("grant_type=").append(wechatConfig.getGrant_type());
        response.setContentType("text/html;charset=utf-8");
        String json = UrlUtils.loadURL(accessTokenBuilder.toString());
        Map<String,Object> wechatToken = JSON.parseObject(json, Map.class);
        try {
            //验证本地库是否存在该用户
            String openId = wechatToken.get("openid").toString();
            String accessToken = wechatToken.get("access_token").toString();
            UsersDto user = userService.loginByWechat(accessToken, openId);
            String token = user.getToken();
            //返回前端处理
            String loginPage = wechatConfig.getSuccess_url() + "?token=" + token;
            response.sendRedirect(loginPage);
        } catch (Exception e) {
            log.error("微信回调错误：{}" , e);
        }
    }



    /**
     * 退出登录
     * @param request
     * @return
     */
    @ApiOperation(value = "退出登录", produces = "application/json", notes = "退出登录")
    @ApiImplicitParam(paramType = "header",name = "token", value = "用户token", required = true, dataType = "String")
    @PostMapping("/logout")
    public Result logout(HttpServletRequest request) {
        log.info("退出登录");
        Result result = ResultUtils.returnFail();
        String token = request.getHeader("token");
        String key = RedisKeyUtils.formatKeyWithPrefix(Constants.Redis.PREFIX_TOKEN, token);
        if(redisUtils.getValue(key) == null){
            log.info("要退出登录的用户未登录");
            return ResultUtils.returnResult(ResultEnum.FILE_NOT_LOGIN);
        }
        int succ = userService.logout(token);
        if (succ > 0) {
            result = ResultUtils.returnSuccess();
        }
        return result;
    }


    /**
     * 获取用户详情
     * @param request
     * @return
     */
    @ApiOperation(value = "获取用户详情", produces = "application/json", notes = "获取用户详情")
    @GetMapping("/info")
    public Result<UsersDto> info(HttpServletRequest request) {
        Result result = null;
        String token = request.getHeader("token");
        UsersDto usersDto = userService.getLoginUser(token);
        if (usersDto != null) {
            usersDto.setPassword(null);
            result = ResultUtils.returnDataSuccess(usersDto);
        }else{
            log.info("用户未登录");
            return ResultUtils.returnResult(ResultEnum.FILE_NOT_LOGIN);
        }
        return result;
    }

    /**
     * 验证手机号
     *
     * @param phone
     */
    private boolean checkPhone(String phone) {
        return phone.matches(Constants.PHONE_REGSTR);
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

}
