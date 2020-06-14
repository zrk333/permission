package com.zrk.controller;

import com.zrk.model.web.ResultStatus;
import com.zrk.request.PermissionModuleRequest;
import com.zrk.service.PermissionModuleService;
import com.zrk.service.TreeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/8
 */
@RestController
@RequestMapping("core/permission/module")
public class PermissionModuleController {
    
    @Resource
    private PermissionModuleService permissionModuleService;

    @Resource
    private TreeService treeService;

    @PostMapping("addModule")
    public ResultStatus addModule(@RequestBody @Validated PermissionModuleRequest request){
        return permissionModuleService.addModule(request);
    }

    @PostMapping("editModule")
    public ResultStatus editModule(@RequestBody @Validated PermissionModuleRequest request){
        return permissionModuleService.editModule(request);
    }

    @GetMapping("deleteModule")
    public ResultStatus deleteModule(@RequestParam("id") Long id){
        return permissionModuleService.deleteModule(id);
    }

    @GetMapping("tree")
    public ResultStatus tree(){
        return treeService.getPermissionModuleTree();
    }
}
