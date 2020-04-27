package cn.est.interceptor;

import cn.est.constants.Constants;
import cn.est.constants.ResultEnum;
import cn.est.utils.ResponseOutput;
import cn.est.utils.reids.RedisKeyUtils;
import cn.est.utils.reids.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 前端拦截器
 */

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    Logger log = LoggerFactory.getLogger(LoginInterceptor.class);


    /**
     * 直接放行的接口
     */
    public static String[] EXCLUDE_URI = new String[]{"/pay/alipay/notify", "/pay/alipay/return", "/user/wechat/callback"};

    /**
     * 需要登录才可以访问的接口
     */
    public static String[] VERIFY_URI = new String[]{"/evaluate/assess", "/order/maintain/", "/pay/aliPay", "/user/logout"};

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        boolean flag = false;
        String loginUser = null;
        String key = null;
        Map<String, String> params = new HashMap<>();
        Enumeration<String> names =  request.getHeaderNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = request.getHeader(name);
            params.put(name, value);
        }
        // 获取访问url
        String url = request.getRequestURL().toString();
        // 判断是否直接放行
        for (String s : EXCLUDE_URI) {
            if (url.contains(s)) {
                return true;
            }
        }

        if (StringUtils.isBlank(params.get("timestamp"))) {
            log.error("时间戳不能为空");
            ResponseOutput.outputJson(response, ResultEnum.FAIL_TIMESTAMP_NOT_NULL);
            return false;
        } else if (StringUtils.isBlank(params.get("source-type"))) {
            log.error("访问来源不能为空");
            ResponseOutput.outputJson(response, ResultEnum.FAIL_VISIT_SOURCE_NOT_NULL);
            return false;
        }

        for (String s : VERIFY_URI) {
            if (url.contains(s)) {
                flag = true;
                break;
            }
        }
        // 判断，如果是需要拦截的方法
        if (flag) {
            String token = request.getHeader("token");
            key = RedisKeyUtils.formatKeyWithPrefix(Constants.Redis.PREFIX_TOKEN, token);
            // 获取登录用户
            loginUser = redisUtils.getValue(key);
            log.info("Basenterceptor-->获取到用户登录信息users:{}", loginUser);
            // 验证用户登陆
            if (null == loginUser) {
                ResponseOutput.outputJson(response, ResultEnum.FILE_NOT_LOGIN);
                return false;
            }
        }
        // 如果用户是登录状态，登录状态续命
        if(!StringUtils.isBlank(key) && !StringUtils.isBlank(loginUser)){
            redisUtils.putValue(key, loginUser, Constants.Duration.HALF_AN_HOUR);
        }
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                           Object arg2, ModelAndView arg3) throws Exception {
        // TODO Auto-generated method stub
    }

}