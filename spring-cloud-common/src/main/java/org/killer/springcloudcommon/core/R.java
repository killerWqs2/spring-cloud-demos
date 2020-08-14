package org.killer.springcloudcommon.core;

import java.util.HashMap;

/**
 * @author wqs
 * @date 2020/7/14-9:42
 * @description
 */
public class R extends HashMap<String, Object> {

    protected R() {
    }

    protected R(long code, String message) {
        put("code", code);
        put("message", message);
    }

    protected R(long code, String message, Object result) {
        put("code", code);
        put("message", message);
        put("result", result);
    }

    public static R success() {
        return new R(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());
    }

    public static R success(Object result) {
        return new R(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), result);
    }

    /**
     * 成功返回结果
     *
     * @param result 获取的数据
     * @param  message 提示信息
     */
    public static R success(Object result, String message) {
        return new R(ResultCode.SUCCESS.getCode(), message, result);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     */
    public static R failed(ResultCode errorCode) {
        return new R(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static R failed(String message) {
        return new R(ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     */
    public static R failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static R validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     * @param message 提示信息
     */
    public static R validateFailed(String message) {
        return new R(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static R unauthorized(Object result) {
        return new R(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), result);
    }

    /**
     * 未授权返回结果
     */
    public static R forbidden(Object result) {
        return new R(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), result);
    }

}

