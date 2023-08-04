package com.xfzcode.genie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xfzcode.genie.entity.User;
import com.xfzcode.genie.entity.UserRole;
import com.xfzcode.genie.mapper.UserMapper;
import com.xfzcode.genie.mapper.UserRoleMapper;
import com.xfzcode.genie.service.UserRoleService;
import com.xfzcode.genie.service.UserService;
import com.xfzcode.genie.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: XMLee
 * @Date: 2023/7/25 10:27
 * @Description:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserRoleService userRoleService;

    @Override
    @Transactional
    public boolean saveUser(UserVo user) {
        User u = new User();
        BeanUtils.copyProperties(user, u);
        if (this.save(u)) {
            List<Long> roleIds = user.getRoleIds();
            List<UserRole> userRoleList = roleIds.stream().map(a -> {
                UserRole userRole = new UserRole();
                userRole.setRoleId(a);
                userRole.setUserId(u.getId());
                return userRole;
            }).collect(Collectors.toList());
            return userRoleService.saveBatch(userRoleList);
        }
        return false;
    }

    @Override
    public boolean updateUserById(UserVo user) {
        User u = new User();
        BeanUtils.copyProperties(user, u);
        if (this.updateById(u)) {
            userRoleService.remove(new QueryWrapper<>().)
            List<Long> roleIds = user.getRoleIds();
            List<UserRole> userRoleList = roleIds.stream().map(a -> {
                UserRole userRole = new UserRole();
                userRole.setRoleId(a);
                userRole.setUserId(u.getId());
                return userRole;
            }).collect(Collectors.toList());
            return userRoleService.saveBatch(userRoleList);
        }
        return false;e;
    }
}
