package com.zrk.config.exception;

import com.zrk.exception.ZrkCoreException;
import com.zrk.model.web.ResultStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 统一异常处理类
 * @Author: zrk
 * @Date: 2020/6/5
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandleAdvice{

    @ExceptionHandler({Exception.class})
    public ResultStatus handler(HttpServletRequest request, Exception e){
        ResultStatus resultStatus = new ResultStatus();
        if(e instanceof ZrkCoreException){
            resultStatus.setStatus(((ZrkCoreException) e).getCode());
            resultStatus.setMsg(e.getMessage());
        } else {
            resultStatus.setStatus(ResultStatus.GlobalStatus.ERROR.getValue());
        }
        return resultStatus;
    }
}
