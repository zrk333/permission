package com.zrk.service.impl;

import com.zrk.config.webmvc.RequestHolder;
import com.zrk.dao.UserMapper;
import com.zrk.exception.InvalidParamException;
import com.zrk.model.User;
import com.zrk.model.web.PageResult;
import com.zrk.model.web.ResultStatus;
import com.zrk.request.UserListRequest;
import com.zrk.request.UserRequest;
import com.zrk.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
        if(checkPhoneExist(request.getPhone(),null)){
            throw new InvalidParamException("该手机号已被使用");
        }
        if(checkEmailExist(request.getEmail(),null)){
            throw new InvalidParamException("该邮箱已被使用");
        }
        // TODO 生成密码、密码加密、<密码加盐>
        String password = "123456";
        User user = buildDO4Add(request,password);
        user.setCreateUserId(RequestHolder.getCurrentUser().getId());
        user.setUpdateUserId(RequestHolder.getCurrentUser().getId());
        userMapper.insertSelective(user);
        return new ResultStatus();
    }

    @Override
    public ResultStatus editUser(UserRequest request) {
        if(checkPhoneExist(request.getPhone(),request.getUserId())){
            throw new InvalidParamException("该手机号已被使用");
        }
        if(checkEmailExist(request.getEmail(),request.getUserId())){
            throw new InvalidParamException("该邮箱已被使用");
        }
        User userOld = userMapper.selectByPrimaryKey(request.getUserId());
        if(userOld == null){
            throw new InvalidParamException("未获取到待更新用户");
        }
        User userNew = buildDO4Update(request);
        userNew.setUpdateUserId(RequestHolder.getCurrentUser().getId());
        userMapper.updateByPrimaryKeySelective(userNew);
        return new ResultStatus();
    }

    @Override
    public ResultStatus deleteUser(Long id) {
        if(userMapper.deleteByPrimaryKey(id) > 0){
            return new ResultStatus();
        }
        return new ResultStatus(ResultStatus.GlobalStatus.ERROR,"删除用户失败");
    }

    @Override
    public ResultStatus getUserListByDeptId(UserListRequest request) {
        Integer count = userMapper.findUserByDeptId(request.getDeptId());
        if(count > 0){
            List<User> userList = userMapper.getUserListByDeptId(request.getDeptId(),request.getFrom(),request.getPageSize());
            if(!CollectionUtils.isEmpty(userList)){
                PageResult<User> pageResult = new PageResult<>();
                pageResult.setData(userList);
                pageResult.setTotal(count);
                ResultStatus resultStatus = new ResultStatus();
                resultStatus.setData(pageResult);
                return resultStatus;
            }
        }
        return new ResultStatus(ResultStatus.GlobalStatus.RESULT_EMPTY);
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


    private User buildDO4Update(UserRequest request) {
        return User.builder()
                .id(request.getUserId())
                .userName(request.getUserName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .deptId(request.getDeptId())
                .status(request.getStatus())
                .remark(request.getRemark())
                .updateTime(new Date())
                .build();
    }
}
