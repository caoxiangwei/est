package cn.est.interceptor;

import cn.est.dto.Result;
import cn.est.constants.ResultEnum;
import cn.est.utils.ResultUtils;
import cn.est.utils.ResponseOutput;
import cn.est.dto.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 统一异常处理
 */
@Component
public class ExceptionInterceptor extends HandlerInterceptorAdapter {

    Logger log = LoggerFactory.getLogger(ExceptionInterceptor.class);

    /**
     * 异常处理
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws java.io.IOException
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Result result = null;
        log.info(request.getRequestURI() + ">>>>>>");
        log.info(request.getRequestURL() + ">>>>>>");
        //拦截异常信息
        if (ex != null) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=utf-8");
            try {
                String moduleMessage = ex.toString();
                log.info("统一异常信息：{}", ex);
                if (ex instanceof BaseException) {
                    BaseException se = (BaseException) ex;
                    result = ResultUtils.returnFail(se.getErrorMessage(), se.getErrorCode());
                } else {
                    result = ResultUtils.returnResult(ResultEnum.FAIL);
                }
            } catch (Exception e) {
                if (!(ex instanceof BaseException)) {
                    log.error("未知错误：{}", ex);
                    result = ResultUtils.returnResult(ResultEnum.COMMON_EXCEPTION);
                }
            }
            ResponseOutput.outputJson(response, result);
        }
    }
}
