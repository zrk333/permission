package com.zrk.model.web;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一封装返回值对象
 * @Description: 返回对象包装类
 * @Author: zrk
 * @Date: 2020/3/16
 */
@Data
public class ResultStatus implements Serializable{

    private static final long serialVersionUID = 7029426057107993093L;

    /**状态码*/
    private Integer status;

    /**信息*/
    private String msg = "";

    /**数据*/
    private Object data;

    public enum GlobalStatus {
        /**成功*/
        SUCCESS(0),
        /**参数错误*/
        PARAM_INVALID(4),
        /**服务器错误*/
        ERROR(5),
        /**结果为空*/
        RESULT_EMPTY(3)
        ;

        public Integer value;

        GlobalStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }


    public ResultStatus(){
        this.status = GlobalStatus.SUCCESS.getValue();
        this.msg = map.get(this.status);
        this.data = null;
    }

    public ResultStatus(GlobalStatus status) {
        this.status = status.getValue();
        this.msg = map.get(status.getValue());
        this.data = null;
    }

    public ResultStatus(GlobalStatus status, String msg) {
        this.status = status.getValue();
        this.msg = msg;
        this.data = null;
    }

    public static final Map<Integer, String> map = new HashMap<Integer, String>(){
        {
            put(GlobalStatus.SUCCESS.getValue(),"成功");
            put(GlobalStatus.PARAM_INVALID.getValue(),"参数错误");
            put(GlobalStatus.ERROR.getValue(),"服务器错误");
            put(GlobalStatus.RESULT_EMPTY.getValue(),"未获取到相关数据");
        }
    };

}
