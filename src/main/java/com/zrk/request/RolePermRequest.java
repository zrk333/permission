package com.zrk.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/14
 */
@Data
public class RolePermRequest {

    /**
     * 角色id
     */
    @NotNull(message = "参数错误：角色id不能为空")
    private Long roleId;

    /**
     * 权限列表
     */
    private List<Long> permissionIds;
}
