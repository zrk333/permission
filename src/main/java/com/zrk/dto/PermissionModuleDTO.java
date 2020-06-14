package com.zrk.dto;

import com.zrk.model.PermissionModule;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/8
 */
@Data
public class PermissionModuleDTO extends PermissionModule {

    /**
     * 子层级
     */
    private List<PermissionModuleDTO> permissionModuleList;

    /**
     * 权限列表
     */
    private List<PermissionsDTO> permissionList;
}
