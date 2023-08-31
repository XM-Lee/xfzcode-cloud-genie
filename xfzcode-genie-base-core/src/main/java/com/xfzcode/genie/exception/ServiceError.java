package com.xfzcode.genie.exception;

/**
 * @Author: XMLee
 * @Date: 2023/8/17 16:06
 * @Description:
 */
public interface  ServiceError {
    /**
     * 获取状态码
     *
     * @return int
     */
    int getCode();

    /**
     * 获取状态信息
     *
     * @return String
     */
    String getErrMessage();
}
