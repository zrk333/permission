package com.zrk.service.impl;

import com.zrk.dao.UserMapper;
import com.zrk.exception.InvalidParamException;
import com.zrk.model.User;
import com.zrk.model.web.ResultStatus;
import com.zrk.request.UserRequest;
import com.zrk.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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
    public ResultStatus addUser(UserRequest request) {
        if(checkPhoneExist(request.getPhone(),request.getUserId())){
            throw new InvalidParamException("该手机号已被使用");
        }
        if(checkEmailExist(request.getEmail(),request.getUserId())){
            throw new InvalidParamException("该邮箱已被使用");
        }
        // TODO 生成密码、密码加密、<密码加盐>
        String password = "123456";
        User user = buildDO4Add(request,password);
        user.setCreateUserId(0L);
        user.setUpdateUserId(0L);
        userMapper.insertSelective(user);
        return new ResultStatus();
    }

    @Override
    public ResultStatus editUser(UserRequest request) {
        return null;
    }

    @Override
    public ResultStatus deleteUser(Long id) {
        return null;
    }


    private Boolean checkPhoneExist(String phone, Long userId) {
        return userMapper.findUserByPhone(phone, userId) > 0;
    }

    private Boolean checkEmailExist(String email, Long userId) {
        return userMapper.findUserByEmail(email ,userId) > 0;
    }

    private User buildDO4Add(UserRequest request, String password) {
        return User.builder()
                .userName(request.getUserName())
                .password(password)
                .phone(request.getPhone())
                .email(request.getEmail())
                .deptId(request.getDeptId())
                .status(request.getStatus())
                .remark(request.getRemark())
                .createTime(new Date())
                .updateTime(new Date())
                .build();
    }
}
