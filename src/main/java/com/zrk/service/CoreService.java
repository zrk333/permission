package com.zrk.service;

import com.zrk.model.Permissions;

import java.util.List;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/14
 */
public interface CoreService {

    /**
     * 获取当前用户已分配的权限
     * @return
     */
    List<Permissions> getCurrentUserPermissionList();

    /**
     * 获取指定角色分配的权限
     * @param roleId
     * @return
     */
    List<Permissions> getRolePermissionList(Long roleId);
}
