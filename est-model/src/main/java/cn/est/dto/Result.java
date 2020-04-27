package cn.est.dto;

import cn.est.constants.ResultEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 返回信息包装类
 * @param <T>
 */
@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {

    @ApiModelProperty(value = "状态码")
    public String code;

    @ApiModelProperty(value = "状态说明")
    public String msg;

    @ApiModelProperty(value = "数据")
    private T data;

    /**
     * 无参构造
     */
    public Result() {}

    /**
     * 根据code，msg创建一个Resutl
     * @param code
     * @param msg
     */
    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据code，msg，data创建一个Resutl
     * @param code
     * @param msg
     * @param data
     */
    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 根据枚举创建一个Result
     * @param resultEnum
     */
    public Result(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
