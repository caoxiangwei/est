package cn.est.constants;

import java.math.BigDecimal;

/**
 * 一些常量
 */
public class Constants {

    /**
     * 否
     */
    public final static Integer EST_NOT = 0;

    /**
     * 是
     */
    public final static Integer EST_YES = 1;

    /**
     * 女
     */
    public final static Integer est_SEX_WOMEN = 0;

    /**
     * 男
     */
    public final static Integer est_SEX_MAN = 1;


    /**
     * 访问来源--APP
     */
    public final static Integer SOURCE_TYPE_APP = 0;

    /**
     * 访问来源-PC
     */
    public final static Integer SOURCE_TYPE_PC = 1;

    /**
     * 本项目小数位
     */
    public final static Integer DECIMAL_DIGITS = 2;


    /**
     * 首页相关常量
     */
    public static class Home{
        public final  static Integer MODEL_SIZE = 5;
    }

    /**
     * 短信相关
     */
    public static class Sms{
        /**
         * 发送状态--发送失败
         */
        public final static Integer STATUS_SEND_FAILED = 1;

        /**
         * 发送状态--发送成功
         */
        public final static Integer STATUS_SEND_SUCCESS = 2;


        /**
         * 短信类型--注册或登录验证码
         */
        public final static Integer TYPE_REGIST_OR_LOGIN = 0;
        /**
         * 短信类型--修改密码验证码
         */
        public final static Integer TYPE_UP_PASS = 1;
        /**
         * 短信类型--通知
         */
        public final static Integer TYPE_NOTICE = 2;
        /**
         * 短信类型--下单验证码
         */
        public final static Integer TYPE_SUBMIT_CHECK = 3;
        /**
         * 阿里云短信服务返回的Message信息
         */
        public final static String ALI_SMS_RESSULT = "OK";

    }

    /**
     * redis常量
     */
    public static class Redis{
        /**
         * 项目公共 前缀
         */
        public final static String PREFIX = "est";

        /**
         * 短信相关
         */
        public final static String PREFIX_SMS = "sms:";

        /**
         * token相关
         */
        public final static String PREFIX_TOKEN = "token:";


        /**
         * 首页类型树
         */
        public final static String KEY_HOME_TREE = PREFIX + "_home_classifyTree:";


    }

    /**
     * 连接符
     */
    public static class Connnector{
        /**
         * 逗号
         */
        public final static String COMMA_ = ",";

        /**
         * 下划线
         */
        public final static String UNDERLINE = "_";

    }

    /**
     * 时长
     */
    public static class Duration{
        /**
         * 一秒
         */
        public final static Integer SECOND_INT = 1;

        /**
         * 一分钟
         */
        public final static Integer MINUTE_INT = SECOND_INT * 60;

        /**
         * 半个小时
         */
        public final static Integer HALF_AN_HOUR_INT = MINUTE_INT * 30;


        /**
         * 一秒
         */
        public final static Integer SECOND = 1;

        /**
         * 一分钟
         */
        public final static Integer MINUTE = SECOND * 60;

        /**
         * 半个小时
         */
        public final static Integer HALF_AN_HOUR = MINUTE * 30;

    }


    /**
     * 商品型号
     */
    public static class Model{

        /**
         * 商品型号状态
         */
        public static class Status {
            /**
             * 上架
             */
            public final static Integer SALE = 1;

            /**
             * 下架
             */
            public final static Integer SOLD_OUT = 2;
        }
    }



    /**
     * 维修类型
     */
    public static class ProcessType{
        /**
         * 维修
         */
        public final static Integer MAINTAIN = 1;

        /**
         * 更换零件
         */
        public final static Integer REPLACEMENT_PART = 2;
    }

    /**
     * 订单
     */
    public static class Order{

        /**
         * 预约状态
         */
        public static class AppointStatus {

            /**
             * 预约
             */
            public final static Integer APPOINT = 1;

            /**
             * 完成
             */
            public final static Integer FINISH = 2;
        }
        /**
         * 预约时间段
         */
        public static class TemporalInterval {

            /**
             * 上午
             */
            public final static Integer AM = 1;

            /**
             * 中午
             */
            public final static Integer NOON = 2;

            /**
             * 下午
             */
            public final static Integer PM = 3;

            /**
             * 晚上
             */
            public final static Integer NIGHT = 4;
        }


        /**
         * 支付渠道
         */
        public static class Channel {

            /**
             * 现金
             */
            public final static Integer CASH = 1;

            /**
             * 刷卡
             */
            public final static Integer CARD = 2;

            /**
             * 支付宝
             */
            public final static Integer ALIPAY = 3;

            /**
             * 微信
             */
            public final static Integer WECHAT = 4;
        }


        /**
         * 订金相关
         */
        public static class Subscription {

            /**
             * 最低订金金额
             */
            public final static Integer MINIMUM = 30;

            /**
             * 最低订金金额
             */
            public final static BigDecimal MINIMUM_DECIMAL = new BigDecimal(MINIMUM);

            /**
             * 订金占估算价格的比例
             */
            public final static Double RATIO = 0.1D;

        }


        /**
         * 维修订单状态（1:预约下单,2:支付定金,9:用户取消,10:完成）
         */
        public static class MaintainStatus {

            /**
             * 预约下单
             */
            public final static Integer APPOINT = 1;

            /**
             * 支付定金
             */
            public final static Integer PAY_DEPOSIT = 2;

            /**
             * 用户取消
             */
            public final static Integer CANCLE = 9;

            /**
             * 完成
             */
            public final static Integer FINISH = 10;
        }

        public static class PayStaus {


            /**
             * 未支付
             */
            public final static Integer UN_PAY = 1;

            /**
             * 支付成功
             */
            public final static Integer SUCCESS = 2;

            /**
             * 支付失败
             */
            public final static Integer FAIL = 3;

        }

    }



    /**
     * 手机号简单正则
     */
    public static String PHONE_REGSTR = "^[1][0-9]{10}$";

    /**
     * 密码正则
     */
    public static String PASSWORD_REGSTR = "^([A-Z]|[a-z]|[0-9]|[_]){6,10}$";

}
