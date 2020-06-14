package com.zrk.dto;

import com.zrk.model.Permissions;
import lombok.Data;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/14
 */
@Data
public class PermissionsDTO extends Permissions {

    /**
     * 是否默认选中
     */
    private Boolean checked;

    /**
     * 是否有权限操作
     */
    private Boolean hasPerm;
}
