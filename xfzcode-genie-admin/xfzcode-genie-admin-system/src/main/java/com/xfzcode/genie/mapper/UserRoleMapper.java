package com.xfzcode.genie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xfzcode.genie.dto.UserRoleDto;
import com.xfzcode.genie.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Author: XMLee
 * @Date: 2023/4/24 10:27
 * @Description:
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<UserRoleDto> selectRolesByUserId(@Param("userIds") List<Long> userIds);

}
