package com.zrk.service;

import com.zrk.model.web.ResultStatus;
import com.zrk.request.PermissionModuleRequest;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/8
 */
public interface PermissionModuleService {

    /**
     * 添加权限模块
     * @param request
     * @return
     */
    ResultStatus addModule(PermissionModuleRequest request);

    /**
     * 编辑权限模块
     * @param request
     * @return
     */
    ResultStatus editModule(PermissionModuleRequest request);

    /**
     * 删除权限模块
     * @param id
     * @return
     */
    ResultStatus deleteModule(Integer id);
}
