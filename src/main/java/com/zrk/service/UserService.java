package com.zrk.service;

import com.zrk.model.web.ResultStatus;
import com.zrk.request.UserListRequest;
import com.zrk.request.UserRequest;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/5
 */
public interface UserService {

    /**
     * 添加用户
     * @param request
     * @return
     */
    ResultStatus addUser(UserRequest request);

    /**
     * 编辑用户
     * @param request
     * @return
     */
    ResultStatus editUser(UserRequest request);

    /**
     * 删除用户
     * @param id
     * @return
     */
    ResultStatus deleteUser(Long id);

    /**
     * 获取指定部门用户列表
     * @param request
     * @return
     */
    ResultStatus getUserListByDeptId(UserListRequest request);
}
