package com.zrk.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/9
 */
@Data
public class PermissionListRequest extends PageRequest{

    /**
     * 权限模块id
     */
    @NotNull(message = "参数错误：权限模块id不能为空")
    private Long permModuleId;
}
