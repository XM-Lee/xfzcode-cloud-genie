package com.xfzcode.genie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xfzcode.genie.entity.Role;
import com.xfzcode.genie.entity.UserRole;
import com.xfzcode.genie.mapper.RoleMapper;
import com.xfzcode.genie.mapper.UserRoleMapper;
import com.xfzcode.genie.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: XMLee
 * @Date: 2023/8/2 10:59
 * @Description:
 */
@Service
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    @Transactional
    public boolean removeRolesByIds(List<Long> roleIds) {
        userRoleMapper.delete(new QueryWrapper<UserRole>().in("role_id", roleIds));
        return this.removeBatchByIds(roleIds);
    }
}
