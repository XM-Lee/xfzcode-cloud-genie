package com.xfzcode.genie.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.google.common.collect.Lists;
import com.xfzcode.genie.api.HttpResult;
import com.xfzcode.genie.api.ResultCode;
import com.xfzcode.genie.api.ResultMessage;
import kotlin.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: XMLee
 * @Date: 2023/8/17 16:05
 * @Description:
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 未登录
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = NotLoginException.class)
    public HttpResult<ServiceError> notLoginExceptionHandler(NotLoginException e) {
        errorFixedPosition(e);
        log.error("_> 错误原因：");
        log.error("_> {}", e.getMessage());
        log.error("=============================错误打印完毕=============================");
        return HttpResult.failed(ResultCode.NO_HAVE_LOGIN_USER);
    }

    /**
     * 角色异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {NotRoleException.class, NotPermissionException.class})
    public HttpResult<ServiceError> notRoleExceptionHandler(Exception e) {
        errorFixedPosition(e);
        log.error("_> 错误原因：");
        log.error("_> {}", e.getMessage());
        log.error("=============================错误打印完毕=============================");
        return HttpResult.failed(ResultCode.NOT_PERMISSION_ERROR);
    }
    /**
     * 处理空指针的异常
     *
     * @param e 参数
     * @return 返回异常信息
     */
    @ExceptionHandler(value = NullPointerException.class)
    public HttpResult<ServiceError> exceptionHandler(NullPointerException e) {
        errorFixedPosition(e);
        log.error("_> 错误原因：");
        log.error("_> {}", e.getMessage());
        log.error("=============================错误打印完毕=============================");
        return HttpResult.failed(ResultCode.SYS_ERROR);
    }

    /**
     * 处理其他异常
     *
     * @param e 参数
     * @return 返回异常信息
     */
    @ExceptionHandler(value = Exception.class)
    public HttpResult<ServiceError> exceptionHandler(Exception e) {
        errorFixedPosition(e);
        log.error("_> 错误原因：");
        log.error("_> {}", e.getMessage());
        log.error("=============================错误打印完毕=============================");
        return HttpResult.failed(ResultCode.SYS_ERROR);
    }

    /**
     * 定位错误发生的位置
     *
     * @param e 错误参数
     */
    private void errorFixedPosition(Exception e) {
        final StackTraceElement stackTrace = e.getStackTrace()[0];
        final String className = stackTrace.getClassName();
        final int lineNumber = stackTrace.getLineNumber();
        final String methodName = stackTrace.getMethodName();
        e.printStackTrace();
        log.error("=============================错误信息如下=============================");
        log.error("_> 异常定位：");
        log.error("_> 类[{}] ==> 方法[{}] ==> 所在行[{}]\n", className, methodName, lineNumber);
    }
}
