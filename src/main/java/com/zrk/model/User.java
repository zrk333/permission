package com.zrk.model;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/5
 */
@Data
public class User {
    private Integer id;

    private String userName;

    private String phone;

    private String email;

    private String password;

    private Integer deptId;

    private Integer status;

    private String remark;

    private Integer createUserId;

    private Integer updateUserId;

    private Date createTime;

    private Date updateTime;

}