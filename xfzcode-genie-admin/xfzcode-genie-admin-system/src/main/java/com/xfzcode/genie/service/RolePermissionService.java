package com.xfzcode.genie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xfzcode.genie.api.HttpResult;
import com.xfzcode.genie.entity.RolePermission;
import com.xfzcode.genie.vo.RolePermissionVo;

/**
 * @Author: XMLee
 * @Date: 2023/8/3 15:42
 * @Description:
 */
public interface RolePermissionService extends IService<RolePermission> {
    HttpResult<?> saveRolePermission(RolePermissionVo rolePermissionVo);
}
