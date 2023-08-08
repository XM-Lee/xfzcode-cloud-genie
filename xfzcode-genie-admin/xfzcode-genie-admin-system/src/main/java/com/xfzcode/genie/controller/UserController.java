package com.xfzcode.genie.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xfzcode.genie.api.HttpResult;
import com.xfzcode.genie.api.ResultMessage;
import com.xfzcode.genie.constant.ApiVersion;
import com.xfzcode.genie.entity.User;
import com.xfzcode.genie.service.UserService;
import com.xfzcode.genie.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;



@RestController
@RequestMapping(ApiVersion.V1_USER)
//@RolesAllowed(Roles.USER_ADMIN)
@Slf4j
@Api(value = "User 用户管理",tags = "1-01.用户管理相关接口-UserController")
public class UserController {


    @Autowired
    private UserService userService;

    /**
     * 获取用户列表数据
     * @param user
     * @param pageSize
     * @param pageSize
     * @return
     */
    @GetMapping
    @ApiOperation("【获取用户列表】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Permission", value = "权限")
    })
    public HttpResult<?> queryPageList(User user, @RequestParam(name="currentPage", defaultValue="1") Integer currentPage,
                                                @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {
        try {

            QueryWrapper<User> wrapper = new QueryWrapper<>();
            if (user.getUserName() != null) {
                wrapper.eq("username", user.getUserName());
            }
            if (user.getPhone() != null) {
                wrapper.eq("user.getPhone()", user.getPhone());
            }
            if (user.getRealName() != null) {
                wrapper.eq("real_name", user.getRealName());
            }
            return HttpResult.success(userService.page(new Page<>(currentPage, pageSize), wrapper));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return HttpResult.error(e.getMessage());
        }
    }

    @PostMapping
    @ApiOperation("【添加用户】")
    public HttpResult<?> add(@RequestBody UserVo user) {
        try {
            String password = user.getPassword();
            user.setPassword(password+"123");
            // TODO 设置用户注册角色
            if (userService.saveUser(user)) {
                return HttpResult.success(user);
            }
            return HttpResult.failed(ResultMessage.SAVE_FAILED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return HttpResult.error(e.getMessage());
        }
    }


    @PutMapping
    @ApiOperation("【编辑用户】")
    public HttpResult<?> edit(@RequestBody UserVo user) {
        try {
            User userInDb= userService.getById(user.getId());
            if(userInDb==null) {
                HttpResult.failed("不存在");
            }else {
                if (userService.updateUserById(user)) {
                    return HttpResult.success(user);
                }
                return HttpResult.failed();
            }
            return HttpResult.failed();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return HttpResult.error(e);
        }
    }

    @DeleteMapping
    @ApiOperation("【删除用户】")
    public HttpResult<?> delete(@RequestParam(name="id") Long id) {
        try {
            if (userService.removeById(id)) {
                return HttpResult.success();
            }
            return HttpResult.failed();
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e);
        }
    }

    @DeleteMapping("/deleteBatch")
    @ApiOperation("【批量删除用户】")
    public HttpResult<?> deleteBatch(@RequestBody List<Long> ids) {

        try {
            if (userService.removeBatchByIds(ids)) {
                return HttpResult.success();
            }
            return HttpResult.failed();
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e);
        }

    }

    /**
     * 冻结&解冻用户
     * @param userIds
     * @return
     */
    //@RequiresRoles({"admin"})
    @ApiOperation("【冻结&解冻用户】")
    @PutMapping("/frozenBatch")
    public HttpResult<?> frozenBatch(@RequestBody List<Long> userIds) {
        try {
            UpdateWrapper<User> queryWrapper = new UpdateWrapper<>();
            queryWrapper.setSql("enable = !enable").in("id", userIds);
            if (userService.update(queryWrapper)) {
                return HttpResult.success();
            }
            return HttpResult.failed();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return HttpResult.error(e);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation("【获取用户详情】")
    public HttpResult<?> queryById(@PathVariable(name = "id") Long id) {
        try {
            User user = userService.getById(id);
            if (user == null) {
                return HttpResult.failed();
            } else {
                return HttpResult.success(userService.getUserByDetail(id));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e);
        }
    }


}
