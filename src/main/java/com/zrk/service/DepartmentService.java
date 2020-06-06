package com.zrk.service;

import com.zrk.model.web.ResultStatus;
import com.zrk.request.DepartmentRequest;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/6
 */
public interface DepartmentService {

    /**
     * 添加部门
     * @param request
     * @return
     */
    ResultStatus addDepartment(DepartmentRequest request);

    /**
     * 编辑部门
     * @param request
     * @return
     */
    ResultStatus editDepartment(DepartmentRequest request);
}
