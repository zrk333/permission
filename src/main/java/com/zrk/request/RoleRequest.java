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
 * @Date: 2020/6/10
 */
@Data
public class RoleRequest {

    /**
     * 角色id
     */
    private Long id;

    /**
     * 角色名称
     */
    @NotEmpty(message = "参数错误：角色名称不能为空")
    private String name;

    /**
     * 类型
     */
    @Min(value = 1, message = "角色类型不合法")
    @Max(value = 2, message = "角色类型不合法")
    private Integer type;

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
