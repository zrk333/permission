package com.zrk.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
    private Long parentId = 0L;

    /**
     * 序列号
     */
    @NotNull(message = "展示顺序不能为空")
    private Integer seq;

    /**
     * 备注
     */
    @Length(max = 200, message = "参数有误：备注长度超过200个字")
    private String remark;
}
