package com.zrk.service.impl;

import com.zrk.model.Permissions;
import com.zrk.service.CoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/14
 */
@Slf4j
@Service
public class CoreServiceImpl implements CoreService {

    @Override
    public List<Permissions> getCurrentUserPermissionList() {
        return null;
    }

    @Override
    public List<Permissions> getRolePermissionList(Long roleId) {
        return null;
    }
}
