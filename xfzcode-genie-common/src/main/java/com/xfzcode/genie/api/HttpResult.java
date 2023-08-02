package com.xfzcode.genie.api;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: XMLee
 * @Date: 2023/7/20 19:02
 * @Description:
 */
@Getter
@Setter
public class HttpResult<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    public HttpResult() {

    }

    public HttpResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> HttpResult<T> success(T data) {
        return new HttpResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> HttpResult<T> success(String message) {
        return new HttpResult<T>(ResultCode.SUCCESS.getCode(), message, null);
    }

    public static <T> HttpResult<T> success(String message,T data) {
        return new HttpResult<T>(ResultCode.SUCCESS.getCode(), message, data);
    }

    public static <T> HttpResult<T> success() {
        return new HttpResult<T>(ResultCode.SUCCESS.getCode(),  ResultCode.SUCCESS.getMessage(), null);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> HttpResult<T> success(T data, String message) {
        return new HttpResult<T>(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     *
     * @param code 成功状态码
     * @param data 返回数据
     * @param message 消息
     * @param <T> 实体
     * @return
     */
    public static <T> HttpResult<T> success(Integer code,T data, String message) {
        return new HttpResult<T>(code, message, data);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     */
    public static <T> HttpResult<T> failed(IErrorCode errorCode) {
        return new HttpResult<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> HttpResult<T> failed(String message) {
        return new HttpResult<T>(ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> HttpResult<T> failed(Integer code,String message) {
        return new HttpResult<T>(code, message, null);
    }

    public static <T> HttpResult<T> failed(T data,String message) {
        return new HttpResult<T>(ResultCode.FAILED.getCode(), message, data);
    }

    public static <T> HttpResult<T> failed(Integer code,T data,String message) {
        return new HttpResult<T>(code, message, data);
    }

    /**
     * 失败返回结果
     */
    public static <T> HttpResult<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 错误返回结果
     */
    public static <T> HttpResult<T> error(T data) {
        return error(ResultCode.ERROR,data);
    }

    public static <T> HttpResult<T> error(IErrorCode errorCode,T data) {
        return new HttpResult<T>(errorCode.getCode(), errorCode.getMessage(), data);
    }


    /**
     * 参数验证失败返回结果
     */
    public static <T> HttpResult<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     * @param message 提示信息
     */
    public static <T> HttpResult<T> validateFailed(String message) {
        return new HttpResult<T>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> HttpResult<T> unauthorized(T data) {
        return new HttpResult<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> HttpResult<T> forbidden(T data) {
        return new HttpResult<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }

    public static <T> HttpResult<T> doing() {
        return new HttpResult<T>(ResultCode.ACCEPTED.getCode(), ResultCode.ACCEPTED.getMessage(), null);
    }


}
