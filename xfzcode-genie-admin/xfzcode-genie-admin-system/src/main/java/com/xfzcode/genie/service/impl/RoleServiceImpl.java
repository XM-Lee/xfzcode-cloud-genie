package com.xfzcode.genie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xfzcode.genie.entity.Role;
import com.xfzcode.genie.mapper.RoleMapper;
import com.xfzcode.genie.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * @Author: XMLee
 * @Date: 2023/8/2 10:59
 * @Description:
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
