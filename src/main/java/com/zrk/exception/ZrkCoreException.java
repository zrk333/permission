package com.zrk.exception;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/5
 */
public class ZrkCoreException extends RuntimeException {

    private Integer code;

    private String message;

    public ZrkCoreException() {
        super();
    }

    public ZrkCoreException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
