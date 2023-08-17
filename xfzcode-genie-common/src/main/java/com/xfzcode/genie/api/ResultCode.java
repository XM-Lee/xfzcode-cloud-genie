package com.xfzcode.genie.api;

/**
 * @Author: XMLee
 * @Date: 2023/7/20 18:20
 * @Description:
 */
public enum ResultCode implements IErrorCode {
    /*============================成功=======================*/
    SUCCESS(200, "操作成功"),
    /*============================失败=======================*/
    FAILED(400, "操作失败"),
    SAVE_FAILED(400, "保存失败"),
    UPDATE_FAILED(400, "编辑失败"),
    DELETE_FAILED(400, "删除失败"),
    ROLE_IS_EXIST(14001, "角色编码已存在"),
    PERMISSION_IS_EXIST(14001, "权限路径已存在"),

    ACCEPTED(202, "操作进行中"),

    /*============================失败=======================*/
    ERROR(1500, "系统错误"),
    SYS_ERROR(500,"系统异常"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    GET_TOKEN_KEY_ERROR(601,"远程获取TokenKey异常"),
    GEN_PUBLIC_KEY_ERROR(602,"生成公钥异常"),
    JWT_TOKEN_EXPIRE(603,"token校验异常"),
    TOMANY_REQUEST_ERROR(429,"后端服务触发流控"),
    BACKGROUD_DEGRADE_ERROR(604,"后端服务触发降级"),
    BAD_GATEWAY(502,"网关服务异常"),


    /*============================权限不足=======================*/
    AUTHORIZATION_HEADER_IS_EMPTY(600,"请求头中的token为空"),
    FORBIDDEN(403, "没有相关权限"),
    LOGIN_USER_ILLEGAL(10002, "非法登录用户"),
    LOGIN_EXPIRED(10003, "登录已失效"),
    NO_TOKEN_ERROR(10004, "未传入token"),
    NOT_PERMISSION_ERROR(10005, "权限不足"),
    NO_HAVE_LOGIN_USER(10006, "请登录"),
    USER_PHONE_ERROR(10007, "账号或密码错误");

    private Integer code;
    private String message;

    private ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
