package com.zrk.model;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/5
 */
@Data
public class RolePermission {
    private Integer id;

    private Integer roleId;

    private Integer permId;

    private Integer createUserId;

    private Integer updateUserId;

    private Date createTime;

    private Date updateTime;
}