package com.zrk.controller;

import com.zrk.model.web.ResultStatus;
import com.zrk.request.DepartmentRequest;
import com.zrk.service.DepartmentService;
import com.zrk.service.TreeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/6
 */
@RestController
@RequestMapping("core/department")
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;

    @Resource
    private TreeService treeService;

    @PostMapping("addDepartment")
    public ResultStatus addDepartment(@RequestBody @Validated DepartmentRequest request){
        return departmentService.addDepartment(request);
    }

    @PostMapping("editDepartment")
    public ResultStatus editDepartment(@RequestBody @Validated DepartmentRequest request){
        return departmentService.editDepartment(request);
    }

    @GetMapping("deleteDepartment")
    public ResultStatus deleteDepartment(@RequestParam("id") Long id){
        return departmentService.deleteDepartment(id);
    }

    @GetMapping("tree")
    public ResultStatus tree(){
        return treeService.getDepartmentTree();
    }
}
