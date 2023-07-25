package com.xfzcode.genie.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xfzcode.genie.api.HttpResult;
import com.xfzcode.genie.constant.ApiVersion;
import com.xfzcode.genie.dto.UserInfoParam;
import com.xfzcode.genie.entity.User;
import com.xfzcode.genie.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.xfzcode.genie.api.ResultCode.*;


@RestController
@RequestMapping(ApiVersion.V1_USER)
//@RolesAllowed(Roles.USER_ADMIN)
@Api(value = "01. User 用户管理",tags = "用户管理相关接口-UserController")
public class UserController {


    @Autowired
    private UserService userService;

    /*@Autowired
    private PasswordEncoder passwordEncoder;*/

    @PostMapping()
    @ApiOperation(value = "新增平台管理员", notes = "新增平台管理员")
    public HttpResult<?> addAdminUser(@RequestBody User user) {
        /*String password = user.getPassword();
        user.setPassword();*/
        if (userService.save(user)) {
            return HttpResult.success();
        }
        return HttpResult.failed(SAVE_FAILED);
    }

    @DeleteMapping
    @ApiOperation(value = "删除平台管理员-batch", notes = "删除平台管理员-batch")
    public HttpResult<?> deleteAdminUser(@RequestBody List<Long> ids) {
        if (null == ids || ids.size() <= 0) {
            return HttpResult.failed(VALIDATE_FAILED);
        }
        if (userService.removeBatchByIds(ids)) {
            return HttpResult.success();
        }
        return HttpResult.failed(DELETE_FAILED);
    }

    @PutMapping()
    @ApiOperation(value = "修改平台管理员",notes = "修改平台管理员")
    public HttpResult<?> updateAdminUser() {
        return null;
    }

    @GetMapping()
    @ApiOperation(value = "获取平台管理员", notes = "获取平台管理员")
    public HttpResult<?> getAdminUserList(@RequestParam Integer currentPage,
                                          @RequestParam Integer pageSize,
                                          @RequestParam String account) {
        Page<User> page = new Page<>(currentPage, pageSize);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(account)) {
            wrapper.eq("account", account);
        }
        return HttpResult.success(userService.page(page, wrapper));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "获取平台管理员详情",notes = "获取平台管理员详情")
    public HttpResult<?> getAdminUserDetail(@PathVariable String id) {
        return HttpResult.success(userService.getById(id));
    }
}
