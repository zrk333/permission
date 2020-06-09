package com.zrk.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/9
 */
@Data
public class PermissionRequest {

    /**
     * 权限id
     */
    private Long id;

    /**
     * 权限名称
     */
    @NotEmpty(message = "参数错误：权限名称不能为空")
    private String name;

    /**
     * 权限模块id
     */
    @NotNull(message = "参数错误：权限模块id不能为空")
    private Integer permModuleId;

    /**
     * url
     */
    private String url;

    /**
     * 类型
     */
    @NotNull(message = "参数错误：权限类型不能为空")
    @Min(value = 1, message = "权限类型不合法")
    @Max(value = 3, message = "权限类型不合法")
    private Integer type;

    /**
     * 状态
     */
    @NotNull(message = "参数错误：权限状态不能为空")
    @Min(value = 0, message = "用户状态有误")
    @Max(value = 2, message = "用户状态有误")
    private Integer status;

    /**
     * 权限序列
     */
    @NotNull(message = "参数错误：权限序列不能为空")
    private Integer seq;

    /**
     * 备注
     */
    @Length(max = 200, message = "参数有误：备注长度超过200个字")
    private String remark;
}
