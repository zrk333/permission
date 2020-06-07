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
public class Department {
    private Long id;

    private String name;

    private Long parentId;

    private String level;

    private Integer seq;

    private String remark;

    private Long createUserId;

    private Long updateUserId;

    private Date createTime;

    private Date updateTime;
}