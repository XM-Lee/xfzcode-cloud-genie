package com.xfzcode.genie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xfzcode.genie.dto.UserRoleDto;
import com.xfzcode.genie.entity.User;
import com.xfzcode.genie.vo.UserRoleVo;
import com.xfzcode.genie.vo.UserVo;

import java.util.List;

/**
 * @Author: XMLee
 * @Date: 2023/7/25 10:27
 * @Description:
 */
public interface UserService extends IService<User> {
    boolean saveUser(UserVo user);

    boolean updateUserById(UserVo user);

    boolean removeById(Long userId);

    boolean removeBatchByIds(List<Long> userIds);

    UserRoleVo getUserByDetail(Long userId);
}
