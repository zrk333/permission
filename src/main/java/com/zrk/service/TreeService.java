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
}
