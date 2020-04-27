package cn.est.utils;

import cn.est.dto.Result;
import cn.est.constants.ResultEnum;

/**
 * 用于返回Result的工具类
 */
public class ResultUtils {


    /**
     * 统一返回成功的Result
     */
    public static Result returnSuccess() {
        Result Result = new Result(ResultEnum.SUCCESS);
        return Result;
    }

    /**
     * 统一返回成功的Result 带数据
     */
    public static Result returnSuccess(String msg, Object data) {
        Result Result = new Result(ResultEnum.SUCCESS.getCode(), msg, data);
        return Result;
    }

    /**
     * 统一返回成功的Result 不带数据
     */
    public static Result returnSuccess(String msg) {
        Result Result = new Result(ResultEnum.SUCCESS.getCode(), msg);
        return Result;
    }

    /**
     * 统一返回成功的Result 带数据 没有消息
     */
    public static Result returnDataSuccess(Object data) {
        Result Result = new Result(ResultEnum.SUCCESS);
        Result.setData(data);
        return Result;
    }

    /**
     * 返回一个失败的Result
     * @return
     */
    public static Result returnFail() {
        Result Result = new Result(ResultEnum.FAIL);
        return Result;
    }

    /**
     * 返回一个失败的Result
     *
     * @param msg
     * @param code
     * @return
     */
    public static Result returnFail(String msg, String code) {
        Result Result = new Result(code, msg);
        return Result;
    }

    /**
     * 返回 一个失败的Result，code统一为 “1”，msg自定义
     *
     * @param msg
     * @return
     */
    public static Result returnFail(String msg) {
        Result Result = new Result(ResultEnum.FAIL.getCode(), msg);
        return Result;
    }

    /**
     * 根据枚举创建一个Result
     *
     * @param resultEnum
     * @return
     */
    public static Result returnResult(ResultEnum resultEnum) {
        Result Result = new Result(resultEnum);
        return Result;
    }

}
