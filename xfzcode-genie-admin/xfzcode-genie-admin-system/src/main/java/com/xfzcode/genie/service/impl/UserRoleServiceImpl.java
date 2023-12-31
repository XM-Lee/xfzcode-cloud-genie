package com.xfzcode.genie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xfzcode.genie.dto.UserRoleDto;
import com.xfzcode.genie.entity.UserRole;
import com.xfzcode.genie.mapper.UserRoleMapper;
import com.xfzcode.genie.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: XMLee
 * @Date: 2023/8/4 17:55
 * @Description:
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
    @Override
    public List<UserRoleDto> selectRolesByUserId(List<Long> userIds) {
        return this.baseMapper.selectRolesByUserId(userIds);
    }
}
