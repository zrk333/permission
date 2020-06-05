package com.zrk.service;

import com.zrk.model.web.ResultStatus;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/5
 */
public interface UserService {

    /**
     * 根据用户id获取用户信息
     * @param userId
     * @return
     */
    ResultStatus getUserInfoById(Long userId);
}
