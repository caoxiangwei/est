package cn.est.config;

import cn.est.utils.StringUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 入参校验配置
 * @Date 2019-08-28 09:45
 * @Author Liujx
 * Version 1.0
 **/
@Configuration
@ConfigurationProperties(prefix = "param")
public class ValidateParamConfig {

    /**
     * 非空的接口及参数
     */
    private List<String> notNull;

    /**
     * 获取非空配置
     * @return
     */
    public Map<String,List<String>> getNotNulls(){
        Map<String,List<String>> map = new HashMap();
        for (String s:notNull){
            String [] arr = s.split("=");
            if(arr != null && arr.length == 2){
                List<String> params = StringUtil.string2StringList(arr[1], "&");
                map.put(arr[0], params);
            }
        }
        return map;
    }


    public List<String> getNotNull() {
        return notNull;
    }

    public void setNotNull(List<String> notNull) {
        this.notNull = notNull;
    }
}
