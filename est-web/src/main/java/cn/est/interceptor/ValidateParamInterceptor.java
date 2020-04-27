package cn.est.interceptor;

import cn.est.utils.ResultUtils;
import cn.est.utils.ResponseOutput;
import cn.est.config.ValidateParamConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 参数校验
 */
@Component
public class ValidateParamInterceptor extends HandlerInterceptorAdapter {

    Logger log = LoggerFactory.getLogger(ValidateParamInterceptor.class);


    @Autowired
    private ValidateParamConfig validateParamConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        //获取URL
        String servletPath = request.getServletPath();
        //先判断空值
        Map<String, String> reqParams = new HashMap<>();
        Enumeration<String> names =  request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = request.getParameter(name);
            reqParams.put(name, value);
        }
        // 从静态代码中获取非空配置
//        Map<String, List<String>> notNullParams = notNullParems();
        // 从配置文件中获取非空配置
        Map<String, List<String>> notNullParams = validateParamConfig.getNotNulls();
        for(String key :notNullParams.keySet()){
            // 如果这个接口有需要判断非空的参数
            if(servletPath.equals(key)){
                for (String notNullParam : notNullParams.get(key)) {
                    // 如果没有入参或者入参中不包括必传参数
                    if(reqParams == null || !reqParams.containsKey(notNullParam)
                            || reqParams.get(notNullParam) == null || StringUtils.isBlank(reqParams.get(notNullParam))){
                        log.info("接口：{}，的参数：【{}】不能为空", servletPath, notNullParam);
                        ResponseOutput.outputJson(response, ResultUtils.returnFail("参数【" + notNullParam + "】不能为空"));
                        return false;
                    }
                }
            }
        }
        return true;
    }


    /**
     * 初始化非空接口和参数
     * @return
     */
    public static Map<String, List<String>> notNullParems(){
        Map<String, List<String>> map = new HashMap<>();
        // 评估维度
        map.put("/api/evaluate/specList", Arrays.asList("modelId"));
        // 估计
        map.put("/api/evaluate/assess", Arrays.asList("modelId", "optionIds"));
        // 维修--下单
        map.put("/api/order/maintain/submit", Arrays.asList("evaluateId", "phone", "appintDate", "temporalInterval", "adress", "sms"));
        // 阿里支付
        map.put("/api/pay/alipay", Arrays.asList("orderNo", "amount"));
        // 验证码登录、注册接口
        map.put("/api/sms/login/sms", Arrays.asList("phone", "sms"));
        // 发送验证码接口
        map.put("/api/sms/verifyCode", Arrays.asList("phone", "codeType"));
        return map;
    }

}
