package com.xfzcode.genie.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xfzcode.genie.api.HttpResult;
import com.xfzcode.genie.api.ResultCode;
import com.xfzcode.genie.constant.ApiVersion;
import com.xfzcode.genie.entity.Role;
import com.xfzcode.genie.service.RoleService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: XMLee
 * @Date: 2023/4/20 9:51
 * @Description:
 */
@RestController
@RequestMapping(ApiVersion.V1_ROLE)
@RequiredArgsConstructor
@Api(value = "01. Role 角色管理", tags = "角色管理相关接口-RoleController")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public HttpResult<?> save(@RequestBody Role role) {
        //TODO 校验唯一值
        try {
            if (roleService.save(role)) {
                return HttpResult.success(role);
            }
            return HttpResult.failed(ResultCode.SAVE_FAILED);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e);
        }
    }

    @DeleteMapping
    public HttpResult<?> delete(@RequestBody List<Long> roleIds) {
        //TODO 需要校验该权限是否被使用着或级联删除
        try{
            if (roleService.removeBatchByIds(roleIds)) {
                return HttpResult.success();
            }
            return HttpResult.failed(ResultCode.DELETE_FAILED);
        }catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e);
        }
    }

    @PutMapping
    public HttpResult<?> update(@RequestBody Role role) {
        try {
            if (roleService.updateById(role)) {
                return HttpResult.success();
            }
            return HttpResult.failed(ResultCode.UPDATE_FAILED);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e);
        }
    }

    @GetMapping
    public HttpResult<?> list( @RequestParam(name="pageNo", defaultValue="1") Integer currentPage,
                               @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                               @RequestParam(name = "roleName",required = false) String roleName) {
        try{
            QueryWrapper<Role> wrapper = new QueryWrapper<>();
            if (StringUtils.isNotBlank(roleName)) {
                wrapper.eq("role_name", roleName);
            }
           return HttpResult.success(roleService.page(new Page<>(currentPage, pageSize), wrapper));
        }catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e);
        }
    }

}
