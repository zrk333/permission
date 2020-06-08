package com.zrk.config.exception;

import com.zrk.exception.ZrkCoreException;
import com.zrk.model.web.ResultStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

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
        } else if(e instanceof MethodArgumentNotValidException){
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            String msg = "参数错误";
            if(!CollectionUtils.isEmpty(fieldErrors)){
                msg = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(";"));
            }
            resultStatus.setStatus(ResultStatus.GlobalStatus.PARAM_INVALID.getValue());
            resultStatus.setMsg(msg);

        } else {
            resultStatus.setStatus(ResultStatus.GlobalStatus.ERROR.getValue());
            resultStatus.setMsg(e.getMessage());
            log.error(e.getMessage(),e);
        }
        return resultStatus;
    }
}
