package cn.est.utils;
import cn.est.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import java.util.*;

/**
 * @Description 关于集合处理的一些工具类
 * @Date 2019-08-16 16:42
 * @Author Liujx
 * Version 1.0
 **/
public class StringUtil {
    /**
     * 根据传入的字符串和切割方式返回一个Long的集合
     * @param s
     * @param cut
     * @return
     */
    public static Set<Long> string2LongSet(String s, String cut) {
        if(org.apache.commons.lang3.StringUtils.isBlank(s) ){
            return null;
        }
        if(org.apache.commons.lang3.StringUtils.isBlank(cut)){
            cut = Constants.Connnector.COMMA_;
        }
        String s_temp = s.trim();
        String[] arr = s_temp.split(cut);
        Set<Long> list = new HashSet<Long>();
        if(arr==null || arr.length<=0){
            return null;
        }
        for(String l : arr){
            if(!StringUtils.isBlank(l)){
                try {
                    list.add(Long.parseLong(l));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }
        return list;
    }
    /**
     * 根据传入的字符串和切割方式返回一个object的集合
     * @param s
     * @param cut
     * @return
     * @author: liujx
     * @date: 2016年5月10日 下午3:36:30
     */
    public static List<String> string2StringList(String s,String cut) {
        if(StringUtils.isBlank(s)){
            return null;
        }
        if(StringUtils.isBlank(cut)){
            cut = Constants.Connnector.COMMA_;
        }
        String s_temp = s.trim();
        String[] arr = s_temp.split(cut);
        List<String> list = new ArrayList<String>();
        if(arr==null || arr.length<=0){
            return null;
        }
        for(String l : arr){
            if(!StringUtils.isBlank(l)){
                list.add(l);
            }
        }
        return list;
    }
    /**
     * Description: 创建一个简单的map对象
     * @param key
     * @param value
     * @return
     * @author liujx
     * @since 2017年5月24日 下午1:18:09
     */
    public static Map<String,Object> createSimpleMap(String key, Object value){
        Map<String,Object> conditions = null;
        if(key !=null && !key.equals("")){
            conditions = new HashMap<String,Object>();
            conditions.put(key, value);
        }
        return conditions;
    }
}
