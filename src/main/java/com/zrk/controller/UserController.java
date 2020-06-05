package com.zrk.controller;

import com.zrk.model.web.ResultStatus;
import com.zrk.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("getUserById")
    private ResultStatus getUserById(@RequestParam("userId") Long userId){
        return userService.getUserInfoById(userId);
    }
}
