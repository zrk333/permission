package com.zrk.dao;


import com.zrk.model.UserDO;
import com.zrk.request.UserRequest;
import org.apache.ibatis.annotations.Param;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/3/16
 */
public interface UserInfoDao {

    /**
     * 获取用户信息
     * @param request
     * @return
     */
    UserDO getUserInfo(@Param("request") UserRequest request);
}
