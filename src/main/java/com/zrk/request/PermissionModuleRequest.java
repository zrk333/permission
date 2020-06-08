package com.zrk.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/8
 */
@Data
public class PermissionModuleRequest {

    /**
     * 权限模块id
     */
    private Long id;

    /**
     * 权限模块名称
     */
    @NotEmpty(message = "参数错误：权限模块名称不能为空")
    private String name;

    /**
     * 权限模块父id
     */
    private Long parentId = 0L;

    /**
     * 序列号
     */
    @NotNull(message = "展示顺序不能为空")
    private Integer seq;

    /**
     * 状态
     */
    @NotNull(message = "参数错误：用户状态不能为空")
    @Min(value = 0, message = "用户状态有误")
    @Max(value = 2, message = "用户状态有误")
    private Integer status;

    /**
     * 备注
     */
    @Length(max = 200, message = "参数有误：备注长度超过200个字")
    private String remark;
}
