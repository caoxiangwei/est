package cn.est.constants;

/**
 * @Description 维修订单状态枚举
 * @Date 2019-08-20 13:17
 * @Author Liujx
 * Version 1.0
 **/
public enum PayChannelEnum {

    CASH(1, "现金"),
    CARD(2, "刷卡"),
    ALIPAY(3, "支付宝"),
    WECHAT(10, "微信");

    Integer code;
    String msg;

    PayChannelEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    public static PayChannelEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (PayChannelEnum e : PayChannelEnum.values()) {
            if (code.equals(e.code)) {
                return e;
            }
        }
        return null;
    }
}
