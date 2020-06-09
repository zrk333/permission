package com.zrk.service;

import com.zrk.model.web.ResultStatus;
import com.zrk.request.PermissionListRequest;
import com.zrk.request.PermissionRequest;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/9
 */
public interface PermissionService {

    /**
     * 添加权限
     * @param request
     * @return
     */
    ResultStatus addPermission(PermissionRequest request);

    /**
     * 编辑权限
     * @param request
     * @return
     */
    ResultStatus editPermission(PermissionRequest request);

    /**
     * 删除权限
     * @param id
     * @return
     */
    ResultStatus deletePermission(Long id);

    /**
     * 获取权限模块下权限列表
     * @param request
     * @return
     */
    ResultStatus getPermissionListByModuleId(PermissionListRequest request);
}
