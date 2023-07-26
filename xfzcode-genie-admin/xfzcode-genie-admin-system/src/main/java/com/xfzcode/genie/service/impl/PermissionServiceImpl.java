package com.xfzcode.genie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xfzcode.genie.entity.Permission;
import com.xfzcode.genie.mapper.PermissionMapper;
import com.xfzcode.genie.service.PermissionService;
import org.springframework.stereotype.Service;

/**
 * @Author: XMLee
 * @Date: 2023/7/26 18:51
 * @Description:
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
}
