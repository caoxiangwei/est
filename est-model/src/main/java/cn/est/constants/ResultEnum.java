package cn.est.constants;

/**
 * 出参错误码列表
 * 尽管是后台信息，文案受制于中前台要求，暴露给用户
 * 接手者请推动前端的后台包装，目前谨慎修改
 *
 * @author liutao
 */
public enum ResultEnum {

    SUCCESS("0", "成功"),
    FAIL("1", "失败"),
    COMMON_EXCEPTION("5", "系统异常"),

    FAIL_PARAM("1001", "请求参数错误"),
    FAIL_LOGIN("1002", "登录失败"),
    FAIL_VERIFY("1003", "验证码错误"),
    FAIL_ACCOUNT_EXIST("1004", "账号已存在"),
    FAIL_ACCOUNT_NOT_EXIST("1005", "账号不存在"),
    FAIL_TIMESTAMP_NOT_NULL("1006", "时间戳不能为空"),
    FAIL_VISIT_SOURCE_NOT_NULL("1007", "访问来源不能为空"),
    FILE_NOT_LOGIN("2001", "未登录"),
    FILE_NOT_HAVE_PERMISSION("2002", "没有权限"),
    FAIL_ACCOUNT_LOCK("2003", "账号已被锁定"),
    FAIL_NOT_FIND_PAGE("3001", "找不到页面"),
    FAIL_TIMEOUT("3002", "请求超时"),
    FAIL_HAVE_DELETED("3003", "已被删除"),
    FAIL_HAVE_NOT_EXIST("3005", "数据不存在");

    String code;
    String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    public static ResultEnum getByCode(String code) {
        if (code == null) {
            return COMMON_EXCEPTION;
        }
        for (ResultEnum outputEnum : ResultEnum.values()) {
            if (code.equals(outputEnum.code)) {
                return outputEnum;
            }
        }
        return COMMON_EXCEPTION;
    }
}
