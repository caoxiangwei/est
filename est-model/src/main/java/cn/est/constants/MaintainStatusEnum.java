package cn.est.constants;

/**
 * @Description 支付渠道枚举
 * @Date 2019-08-20 13:17
 * @Author Liujx
 * Version 1.0
 **/
public enum MaintainStatusEnum {

    APPOINT(1, "预约下单"),
    PAY_DEPOSIT(2, "支付定金"),
    SYSTEM_VALIDATION(3, "后台确认"),
    PHYSICAL_INSPECTION(4, "实物质检"),
    AFFIRM_INSPECTION(5, "用户确认检查结果"),
    MAINTAIN_FINISH(6, "维修完成"),
    AFFIRM_MAINTAIN(7, "用户确认维修结果"),
    PAY_BALANCE(8, "支付尾款"),
    CANCLE(9, "用户取消"),
    FINISH(10, "完成");


    Integer code;
    String msg;

    MaintainStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    public static MaintainStatusEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (MaintainStatusEnum e : MaintainStatusEnum.values()) {
            if (code.equals(e.code)) {
                return e;
            }
        }
        return null;
    }
}
