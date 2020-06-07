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
 * @Date: 2020/3/16
 */
@Data
public class UserRequest {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    @NotEmpty(message = "参数错误：用户姓名不能为空")
    private String userName;

    /**
     * 手机号
     */
    @NotEmpty(message = "参数错误：手机号不能为空")
    private String phone;

    /**
     * 邮箱
     */
    @NotEmpty(message = "参数错误：邮箱不能为空")
    private String email;

    /**
     * 所在部门id
     */
    @NotNull(message = "参数错误：所在部门id不能为空")
    private Long deptId;

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
