package cn.est.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期时间工具类
 */
public class DateUtils {


    /**
     * 日期格式
     */
    public final static String HHMMSS = "HHmmss";
    public final static String HH_MM_SS = "HH:mm:ss";
    public final static String YYYYMMDD = "yyyyMMdd";
    public final static String YYYY_MM_DD = "yyyy-MM-dd";
    public final static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public final static String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";


    /**
     * 获取两个日期之间的日期
     * @param start 开始日期
     * @param end 结束日期
     * @return 日期字符串格式的集合
     */
    public static List<Date> getBetweenDates(Date start, Date end) {
        List<Date> result = new ArrayList<Date>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        while (tempStart.before(tempEnd) || tempStart.equals(tempEnd)) {
            result.add(tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        return result;
    }

    /**
     * 根据日期字符串返回日期
     * @param source
     * @param format
     * @return
     * @throws ParseException
     */
    public static final Date parse(String source,String format) throws ParseException {
        DateFormat df = new SimpleDateFormat(format);
        return df.parse(source);
    }

    /**
     * 根据日期获取格式化的日期字符串
     * @param date
     * @param format
     * @return
     * @throws ParseException
     */
    public static final String format(Date date,String format) throws ParseException {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * Description: 按指定格式生成当前日期的字符串
     *
     * @param formatStr
     *            指定日期格式；如：yyyy-MM-dd
     * @return 指定格式的字符串
     * @author shijb
     * @since 2016年8月12日 下午6:09:01
     */
    public static String parseCurrentDate(String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(new Date());
    }

    /**
     * Description: 按指定格式生成当前日期
     *
     * @param formatStr 指定日期格式；如：yyyy-MM-dd
     * @return 当前日期
     * @since 2016年8月12日 下午6:11:35
     */
    public static Date getCurrentDate(String formatStr) {
        return DateUtils.parseDate(parseCurrentDate(formatStr), formatStr);
    }

    /**
     * Description: 将字符串转换为日期格式。
     *
     * @param strDate 字符串
     * @param pattern 日期格式
     * @return 日期对象
     * @since 2016年6月23日 上午11:23:39
     */
    public static Date parseDate(String strDate, String pattern) {
        Date date = null;
        try {
            date = new SimpleDateFormat(pattern).parse(strDate);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return date;
    }


    /**
     * 日期计算
     *
     * @param date
     * @param field
     * @param amount
     * @return
     */
    public static final Date addDate(Date date, int field, int amount) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }


    /**
     * 日期计算
     * @param date
     * @param amount
     * @return
     */
    public static final Date addDate(Date date, int amount) {

        return addDate(date,Calendar.DATE, amount);
    }

}
