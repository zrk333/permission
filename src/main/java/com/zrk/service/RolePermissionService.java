package com.zrk.service;

import com.zrk.model.web.ResultStatus;
import com.zrk.request.RolePermRequest;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/14
 */
public interface RolePermissionService {

    /**
     * 更新角色绑定权限
     * @param request
     * @return
     */
    ResultStatus updateRolePerms(RolePermRequest request);
}
