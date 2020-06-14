package com.zrk.exception;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/14
 */
public class ResultEmptyException extends ZrkCoreException {

    public ResultEmptyException() {
        super(Integer.valueOf(5),"查询结果为空");
    }

}
