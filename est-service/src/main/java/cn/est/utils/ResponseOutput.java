package cn.est.utils;

import cn.est.dto.Result;
import cn.est.constants.ResultEnum;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Title:向浏览器输出一个json
 * Description: 类的功能详细描述
 * @author liujx
 * @version 1.0
 * @since 2017年5月23日
 */
public class ResponseOutput {

    static Logger log = LoggerFactory.getLogger(ResponseOutput.class);

    /**
     * 根据枚举对象将数据写会前端
     * @param response
     * @param resultEnum
     */
    public static void outputJson(HttpServletResponse response, ResultEnum resultEnum) {
        outputJson(response, ResultUtils.returnResult(resultEnum));
    }


    /**
     * 根据返回对象将数据写会前端
     * @param response
     * @param result
     */
    public static void outputJson(HttpServletResponse response, Result result) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(JSONObject.toJSON(result).toString());
        } catch (IOException e) {
            log.error("ResponseOut-->error:{}", e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}