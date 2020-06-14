package com.zrk.controller;

import com.zrk.model.web.ResultStatus;
import com.zrk.request.RoleRequest;
import com.zrk.service.RoleService;
import com.zrk.service.TreeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/10
 */
@RestController
@RequestMapping("core/role")
public class RoleController {
    
    @Resource
    private RoleService roleService;

    @Resource
    private TreeService treeService;

    @PostMapping("addRole")
    public ResultStatus addRole(@RequestBody @Validated RoleRequest request){
        return roleService.addRole(request);
    }

    @PostMapping("editRole")
    public ResultStatus editRole(@RequestBody @Validated RoleRequest request){
        return roleService.editRole(request);
    }

    @GetMapping("deleteRole")
    public ResultStatus deleteRole(@RequestParam("id") Long id){
        return roleService.deleteRole(id);
    }

    @GetMapping("list")
    public ResultStatus getRoleList(){
        return roleService.getRoleList();
    }

    @GetMapping("tree")
    public ResultStatus tree(@RequestParam("roleId") Long roleId){
        return treeService.getRoleTree(roleId);
    }


}
