package cn.est.utils;

import cn.est.constants.Constants;

import java.math.BigDecimal;

/**
 * @Description 数字相关工具类
 * @Date 2019-08-22 17:08
 * @Author Liujx
 * Version 1.0
 **/
public class MathUtils {

    /**
     * 返回一个4位随机数
     * @return
     */
    public static String random(){
        int random = (int) ((Math.random() * 9 + 1) * 1000);
        return random + "";
    }


    /**
     * 格式化BigDecimal，返回保留相应的小数
     * @param decimal
     * @param num
     * @return
     */
    public static BigDecimal formatDecimal(BigDecimal decimal, int num){
        BigDecimal result = decimal.setScale(num,BigDecimal.ROUND_HALF_UP);
        return result;
    }

    /**
     * 格式化BigDecimal，默认保留两位小数
     * @param decimal
     * @return
     */
    public static BigDecimal formatDecimal(BigDecimal decimal){
        BigDecimal result = formatDecimal(decimal, Constants.DECIMAL_DIGITS);
        return result;
    }

}
