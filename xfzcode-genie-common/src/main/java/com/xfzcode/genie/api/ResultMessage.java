package com.xfzcode.genie.api;

/**
 * @Author: XMLee
 * @Date: 2023/7/20 18:20
 * @Description:
 */
public interface ResultMessage {
    String SAVE_FAILED = "保存失败";
    String CODE_ERROR = "验证错误或已失效";
    String LOGIN_ERROR = "账号或密码错误";
    String PARAM_MISS = "参数缺失";
    String CODE_NOT_EXPIRE = "验证码未过期";
    String NO_HAVE_LOGIN_USER = "未传入token";
    String LOGIN_USER_ILLEGAL = "非法登录用户";
    String LOGIN_EXPIRED = "登录已失效";
    String NO_TOKEN_ERROR = "未传入token";
    String NOT_PERMISSION_ERROR = "权限不足";
    String NO_DATA_CHANGE = "沒有数据变更";

}
