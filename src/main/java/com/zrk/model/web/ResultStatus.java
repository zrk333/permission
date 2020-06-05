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
        PARAM_INVALID(51),
        /**服务器错误*/
        ERROR(52),
        /**已绑定*/
        BINDED(5),
        /**结果为空*/
        RESULT_EMPTY(3),
        /**登陆失败*/
        LOGIN_ERROR(1),
        /**用户名或密码错误*/
        NOT_EXIST(7),
        /**发送验证码错误*/
        SEND_SMS_ERROR(31),
        /**验证码错误*/
        CODE_ERROR(32)
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
            put(GlobalStatus.LOGIN_ERROR.getValue(),"登陆失败");
            put(GlobalStatus.PARAM_INVALID.getValue(),"参数错误");
            put(GlobalStatus.PARAM_INVALID.getValue(),"服务器错误");
            put(GlobalStatus.RESULT_EMPTY.getValue(),"未获取到相关数据");
            put(GlobalStatus.NOT_EXIST.getValue(),"用户名或密码不正确");
            put(GlobalStatus.BINDED.getValue(),"已经绑定的别的移动设备，请解除绑定后重试");
            put(GlobalStatus.SEND_SMS_ERROR.getValue(),"短信发送不成功");
            put(GlobalStatus.CODE_ERROR.getValue(),"短信验证码过期或者错误");
        }
    };

}
