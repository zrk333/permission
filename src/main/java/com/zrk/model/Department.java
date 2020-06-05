package com.zrk.model;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/5
 */
@Data
public class Department {
    private Integer id;

    private String name;

    private Integer parentId;

    private String level;

    private Integer seq;

    private String remark;

    private Integer createUserId;

    private Integer updateUserId;

    private Date createTime;

    private Date updateTime;
}