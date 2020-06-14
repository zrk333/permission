package com.zrk.controller;

import com.zrk.model.web.ResultStatus;
import com.zrk.request.PermissionListRequest;
import com.zrk.request.PermissionRequest;
import com.zrk.service.PermissionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/9
 */
@RestController
@RequestMapping("core/permission")
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    @PostMapping("addPermission")
    public ResultStatus addPermission(@RequestBody @Validated PermissionRequest request){
        return permissionService.addPermission(request);
    }

    @PostMapping("editPermission")
    public ResultStatus editPermission(@RequestBody @Validated PermissionRequest request){
        return permissionService.editPermission(request);
    }

    @GetMapping("deletePermission")
    public ResultStatus deletePermission(@RequestParam("id") Long id){
        return permissionService.deletePermission(id);
    }

    @GetMapping("getPermissionListByModuleId")
    public ResultStatus getPermissionListByModuleId(PermissionListRequest request){
        return permissionService.getPermissionListByModuleId(request);
    }
}
