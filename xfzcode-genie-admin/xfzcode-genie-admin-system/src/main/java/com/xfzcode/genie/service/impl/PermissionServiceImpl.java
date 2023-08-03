package com.xfzcode.genie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xfzcode.genie.entity.Permission;
import com.xfzcode.genie.entity.RolePermission;
import com.xfzcode.genie.entity.UserRole;
import com.xfzcode.genie.mapper.PermissionMapper;
import com.xfzcode.genie.mapper.RolePermissionMapper;
import com.xfzcode.genie.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: XMLee
 * @Date: 2023/7/26 18:51
 * @Description:
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;


    @Override
    public boolean removePermissionByIds(List<Long> permissionIds) {
        rolePermissionMapper.delete(new QueryWrapper<RolePermission>().in("permission_id", permissionIds));
        return this.removeBatchByIds(permissionIds);
    }
}
