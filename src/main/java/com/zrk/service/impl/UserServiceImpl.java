package com.zrk.service.impl;

import com.zrk.dao.UserMapper;
import com.zrk.exception.InvalidUserIdException;
import com.zrk.model.web.ResultStatus;
import com.zrk.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/5
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public ResultStatus getUserInfoById(Long userId) {
        if(userId == Integer.MAX_VALUE) {
            throw new InvalidUserIdException();
        }
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setData(userMapper.selectByPrimaryKey(userId));
        return resultStatus;
    }
}
