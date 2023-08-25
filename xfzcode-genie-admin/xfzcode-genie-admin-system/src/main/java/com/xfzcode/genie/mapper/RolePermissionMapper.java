package com.xfzcode.genie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xfzcode.genie.api.HttpResult;
import com.xfzcode.genie.entity.RolePermission;
import com.xfzcode.genie.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Author: XMLee
 * @Date: 2023/4/24 10:08
 * @Description:
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    List<User> queryRoleUser(@Param("roleId") Long roleId);

}
