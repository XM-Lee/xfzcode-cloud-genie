package com.xfzcode.genie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xfzcode.genie.dto.UserRoleDto;
import com.xfzcode.genie.entity.UserRole;

import java.util.List;

/**
 * @Author: XMLee
 * @Date: 2023/8/4 17:55
 * @Description:
 */
public interface UserRoleService extends IService<UserRole> {
    List<UserRoleDto> selectRolesByUserId(List<Long> userIds);
}
