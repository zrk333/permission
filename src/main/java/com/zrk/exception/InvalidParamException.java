package com.zrk.exception;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/6
 */
public class InvalidParamException extends ZrkCoreException {

    public InvalidParamException() {
        super(-1,"参数有误");
    }

    public InvalidParamException(String message) {
        super(-1,message);
    }
}
