package cn.est.config;

import cn.est.interceptor.LoginInterceptor;
import cn.est.interceptor.ExceptionInterceptor;
import cn.est.interceptor.ValidateParamInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置类
 */
@Configuration
class InterceptorConfig implements WebMvcConfigurer {

    /**
     * 自定义拦截器，公共参数校验、登录权限校验
     */
    @Autowired
    private LoginInterceptor baseInterceptor;

    /**
     * 自定义拦截器，统一异常处理
     */
    @Autowired
    ExceptionInterceptor exceptionInterceptor;

    /**
     * 自定义拦截器，参数校验
     */
    @Autowired
    private ValidateParamInterceptor validateParamInterceptor;


    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(baseInterceptor).addPathPatterns("/api/**").excludePathPatterns("/api/pay/aliPay/**");

        registry.addInterceptor(validateParamInterceptor).addPathPatterns("/api/**");

        registry.addInterceptor(exceptionInterceptor).addPathPatterns("/**");
    }
}
