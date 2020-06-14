package com.zrk.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/5
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
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