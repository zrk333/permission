package com.zrk.controller;

import com.zrk.model.web.ResultStatus;
import com.zrk.request.DepartmentRequest;
import com.zrk.service.DepartmentService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/6
 */
@RestController
@RequestMapping("user")
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;

    @PostMapping("addDepartment")
    public ResultStatus addDepartment(@RequestBody @Validated DepartmentRequest request){
        return departmentService.addDepartment(request);
    }

    @PostMapping("editDepartment")
    public ResultStatus editDepartment(@RequestBody @Validated DepartmentRequest request){
        return departmentService.editDepartment(request);
    }
}
