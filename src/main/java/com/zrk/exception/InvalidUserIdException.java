package com.zrk.exception;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/5
 */
public class InvalidUserIdException extends ZrkCoreException {
    public InvalidUserIdException() {
        super(Integer.valueOf(400),"用户id有误");
    }
}
