package com.xfzcode.genie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xfzcode.genie.entity.User;
import com.xfzcode.genie.mapper.UserMapper;
import com.xfzcode.genie.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author: XMLee
 * @Date: 2023/7/25 10:27
 * @Description:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
