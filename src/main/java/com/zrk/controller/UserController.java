package com.zrk.controller;

import com.zrk.model.web.ResultStatus;
import com.zrk.request.UserListRequest;
import com.zrk.request.UserRequest;
import com.zrk.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/5
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("addUser")
    public ResultStatus addUser(@RequestBody @Validated UserRequest request){
        return userService.addUser(request);
    }

    @PostMapping("editUser")
    public ResultStatus editUser(@RequestBody @Validated UserRequest request){
        return userService.editUser(request);
    }

    @GetMapping("deleteUser")
    public ResultStatus deleteUser(@RequestParam("id") Long id){
        return userService.deleteUser(id);
    }

    @GetMapping("getUserListByDeptId")
    public ResultStatus getUserListByDeptId(@RequestBody @Validated UserListRequest request){
        return userService.getUserListByDeptId(request);
    }
}
