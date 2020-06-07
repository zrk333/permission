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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private Long id;

    private String userName;

    private String phone;

    private String email;

    private String password;

    private Long deptId;

    private Integer status;

    private String remark;

    private Long createUserId;

    private Long updateUserId;

    private Date createTime;

    private Date updateTime;

}