package cn.est.utils.reids;

import cn.est.constants.Constants;

/**
 * @Description redis key工具类
 * @Date 2019-08-15 11:54
 * @Author Liujx
 * Version 1.0
 **/
public class RedisKeyUtils {

    /**
     * 根据出入的参数创建一个Redis key
     * @return 如果参数为空，那么返回null
     */
    public static String formatKeys(String ... args){
        if (args != null && args.length > 0){
            StringBuilder key = new StringBuilder();
            for (String s: args){
                key.append(s).append(Constants.Connnector.UNDERLINE);
            }
            return key.toString();
        }
        return null;
    }

    /**
     * 根据出入的参数创建一个Redis key，自动拼接前缀
     * @return 如果参数为空，那么返回null
     */
    public static String formatKeyWithPrefix(String ... args){
        if (args != null && args.length > 0){
            StringBuilder key = new StringBuilder(Constants.Redis.PREFIX).append(Constants.Connnector.UNDERLINE);
            for (String s: args){
                key.append(s).append(Constants.Connnector.UNDERLINE);
            }
            return key.toString();
        }
        return null;
    }

}
