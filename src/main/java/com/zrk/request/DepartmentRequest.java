package com.zrk.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/6
 */
@Data
public class DepartmentRequest {

    /**
     * 部门id
     */
    private Long id;

    /**
     * 部门名称
     */
    @NotEmpty(message = "参数错误：部门名称不能为空")
    private String name;

    /**
     * 部门父id
     */
    private Long parentId;

    /**
     * 序列号
     */
    @NotNull(message = "展示顺序不能为空")
    private Integer seq;

    /**
     * 备注
     */
    private String remark;
}
