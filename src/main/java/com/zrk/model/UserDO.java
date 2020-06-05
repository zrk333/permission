package com.zrk.model;

import lombok.Data;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/3/16
 */
@Data
public class UserDO {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;
}
