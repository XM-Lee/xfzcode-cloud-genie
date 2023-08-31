package com.xfzcode.genie.exception;

import com.xfzcode.genie.api.HttpResult;
import com.xfzcode.genie.api.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: XMLee
 * @Date: 2023/8/17 16:05
 * @Description:
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

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
     * 处理实体不存在异常
     *
     * @param e 参数
     * @return 返回异常信息
     */
    @ExceptionHandler(value = EntityNotExistException.class)
    public HttpResult<ServiceError> exceptionHandler(EntityNotExistException e) {
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
