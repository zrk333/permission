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
    private Long id;

    private Long roleId;

    private Long permId;

    private Long createUserId;

    private Long updateUserId;

    private Date createTime;

    private Date updateTime;
}