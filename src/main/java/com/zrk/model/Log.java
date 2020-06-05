package com.zrk.model;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/5
 */
@Data
public class Log {
    private Integer id;

    private Integer type;

    private Integer targetId;

    private String oldValue;

    private String newValue;

    private Integer createUserId;

    private Integer updateUserId;

    private Date createTime;

    private Date updateTime;

    private Boolean status;
}