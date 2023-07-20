package com.xfzcode.genie.exception;

import com.xfzcode.genie.api.IErrorCode;
import lombok.Data;

/**
 * @Author: XMLee
 * @Date: 2023/7/20 18:05
 * @Description: 网关异常类
 */
@Data
public class GateWayException extends RuntimeException{
    private long code;

    private String message;

    public GateWayException(IErrorCode iErrorCode) {
        this.code = iErrorCode.getCode();
        this.message = iErrorCode.getMessage();
    }
}
