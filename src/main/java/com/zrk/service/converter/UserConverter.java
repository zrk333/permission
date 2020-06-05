package com.zrk.service.converter;


import com.zrk.model.UserDO;
import com.zrk.model.UserDTO;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/3/16
 */
public interface UserConverter {

    /**
     * DOToDTO
     * @param userInfo
     * @return
     */
    UserDTO converterDOToDTO(UserDO userInfo);
}
