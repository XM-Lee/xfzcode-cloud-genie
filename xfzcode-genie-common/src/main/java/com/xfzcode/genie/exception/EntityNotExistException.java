package com.xfzcode.genie.exception;

import com.xfzcode.genie.api.IErrorCode;
import com.xfzcode.genie.api.ResultCode;
import com.xfzcode.genie.api.ResultMessage;

/**
 * @Author: XMLee
 * @Date: 2023/8/31 16:37
 * @Description:
 */
public class EntityNotExistException extends RuntimeException{
    private long code;

    private String message;

    public EntityNotExistException(IErrorCode iErrorCode) {
        this.code = iErrorCode.getCode();
        this.message = iErrorCode.getMessage();
    }

    public EntityNotExistException() {
        this.code = ResultCode.ENTITY_NOT_EXIST.getCode();
        this.message = ResultCode.ENTITY_NOT_EXIST.getMessage();
    }
}
