package com.zrk.service;

import com.zrk.model.web.ResultStatus;
import com.zrk.request.RoleRequest;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/10
 */
public interface RoleService {

    /**
     * 添加角色
     * @param request
     * @return
     */
    ResultStatus addRole(RoleRequest request);

    /**
     * 编辑角色
     * @param request
     * @return
     */
    ResultStatus editRole(RoleRequest request);

    /**
     * 删除角色
     * @param id
     * @return
     */
    ResultStatus deleteRole(Long id);
}
