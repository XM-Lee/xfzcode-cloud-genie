package com.xfzcode.genie.controller;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xfzcode.genie.api.HttpResult;
import com.xfzcode.genie.api.ResultMessage;
import com.xfzcode.genie.constant.ApiVersion;
import com.xfzcode.genie.constant.CommonConstant;
import com.xfzcode.genie.dto.UserInfoParam;
import com.xfzcode.genie.entity.User;
import com.xfzcode.genie.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import kotlin.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.xfzcode.genie.api.ResultCode.*;


@RestController
@RequestMapping(ApiVersion.V1_USER)
//@RolesAllowed(Roles.USER_ADMIN)
@Slf4j
@Api(value = "01. User 用户管理",tags = "用户管理相关接口-UserController")
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
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public HttpResult<?> queryPageList(User user, @RequestParam(name="pageNo", defaultValue="1") Integer currentPage,
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
    public HttpResult<?> add(@RequestBody User user) {
        try {
            String password = user.getPassword();
            user.setPassword(password+"123");
                       // 保存用户走一个service 保证事务
            if (userService.save(user)) {
                return HttpResult.success(user);
            }
            return HttpResult.failed(ResultMessage.SAVE_FAILED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return HttpResult.error(e.getMessage());
        }
    }


    @PutMapping
    public HttpResult<?> edit(@RequestBody User user) {
        try {
            User userInDb= userService.getById(user.getId());
            if(userInDb==null) {
                HttpResult.failed("不存在");
            }else {
                if (userService.updateById(user)) {
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

    /**
     * 删除用户
     */
    //@RequiresRoles({"admin"})
    @DeleteMapping
    public HttpResult<?> delete(@RequestParam(name="id",required=true) String id) {
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

    /**
     * 批量删除用户
     */
    //@RequiresRoles({"admin"})
    @DeleteMapping("/deleteBatch")
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
    @PutMapping("/frozenBatch")
    public HttpResult<?> frozenBatch(@RequestBody List<Long> userIds) {
        try {
            UpdateWrapper<User> queryWrapper = new UpdateWrapper<>();
            queryWrapper.set("enabled", 0).in("id", userIds);
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
    public HttpResult<?> queryById(@RequestParam(name = "id", required = true) String id) {
        try {
            User user = userService.getById(id);
            if (user == null) {
                return HttpResult.failed();
            } else {
                return HttpResult.success(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e);
        }
    }

    /*@GetMapping(value = "/queryUserRole/{userid}")
    public Result<List<String>> queryUserRole(@PathVariable(name = "userid") String userid) {

    }*/



    /**
     * 修改密码
     */
    //@RequiresRoles({"admin"})
    /*@RequestMapping(value = "/changePassword", method = RequestMethod.PUT)
    public Result<?> changePassword(@RequestBody SysUser sysUser) {

    }*/

}
