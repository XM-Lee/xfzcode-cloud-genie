package com.xfzcode.genie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xfzcode.genie.entity.Permission;

import java.util.List;

/**
 * @Author: XMLee
 * @Date: 2023/7/26 18:50
 * @Description:
 */
public interface PermissionService extends IService<Permission> {
    boolean removePermissionByIds(List<Long> ids);
}
