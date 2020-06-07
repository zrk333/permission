package com.zrk.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/8
 */
@Data
public class UserListRequest extends PageRequest{

    /**
     * 所在部门id
     */
    @NotNull(message = "参数错误：所在部门id不能为空")
    private Long deptId;
}
