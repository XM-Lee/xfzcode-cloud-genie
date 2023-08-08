package com.xfzcode.genie.api;

/**
 * @Author: XMLee
 * @Date: 2023/7/20 18:20
 * @Description:
 */
public interface ResultMessage  {
    String SAVE_FAILED = "保存失败";
    String CODE_ERROR = "验证错误或已失效";
    String LOGIN_ERROR = "账号或密码错误";
    String PARAM_MISS = "参数缺失";
    String CODE_NOT_EXPIRE = "验证码未过期";
}
