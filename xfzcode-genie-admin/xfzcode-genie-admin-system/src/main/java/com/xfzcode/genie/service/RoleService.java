package com.xfzcode.genie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xfzcode.genie.entity.Role;

import java.util.List;

/**
 * @Author: XMLee
 * @Date: 2023/8/2 10:58
 * @Description:
 */
public interface RoleService extends IService<Role> {
    boolean removeRolesByIds(List<Long> roleIds);
}
