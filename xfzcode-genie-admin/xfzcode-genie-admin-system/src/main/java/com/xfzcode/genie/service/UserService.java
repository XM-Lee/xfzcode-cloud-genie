package com.xfzcode.genie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xfzcode.genie.entity.User;
import com.xfzcode.genie.vo.UserVo;

/**
 * @Author: XMLee
 * @Date: 2023/7/25 10:27
 * @Description:
 */
public interface UserService extends IService<User> {
    boolean saveUser(UserVo user);

    boolean updateUserById(UserVo user);
}
