package com.zrk.service;

import com.zrk.model.web.ResultStatus;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/6
 */
public interface TreeService {

    /**
     * 获取部门树
     * @return
     */
    ResultStatus getDepartmentTree();

    /**
     * 获取权限模块树
     * @return
     */
    ResultStatus getPermissionModuleTree();

    /**
     * 获取角色权限树
     * @param roleId
     * @return
     */
    ResultStatus getRoleTree(Long roleId);
}
