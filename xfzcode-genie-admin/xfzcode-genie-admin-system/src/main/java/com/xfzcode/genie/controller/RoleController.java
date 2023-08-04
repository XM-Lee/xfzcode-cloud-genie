package com.xfzcode.genie.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xfzcode.genie.api.HttpResult;
import com.xfzcode.genie.api.ResultCode;
import com.xfzcode.genie.constant.ApiVersion;
import com.xfzcode.genie.entity.Role;
import com.xfzcode.genie.service.RolePermissionService;
import com.xfzcode.genie.service.RoleService;
import com.xfzcode.genie.vo.RolePermissionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "角色管理", tags = " 1-02角色管理相关接口-RoleController")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePermissionService rolePermissionService;


    @PostMapping
    @ApiOperation("【新增角色】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Role", value = "角色")
    })
    public HttpResult<?> save(@RequestBody Role role) {
        try {
            if (roleService.count(new QueryWrapper<Role>().eq("authority", role.getAuthority())) > 0) {
                return HttpResult.failed(ResultCode.ROLE_IS_EXIST);
            }
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
    @ApiOperation("【删除角色】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleIds", value = "角色id列表")
    })
    public HttpResult<?> delete(@RequestBody List<Long> roleIds) {
        try{
            if (roleService.removeRolesByIds(roleIds)) {
                return HttpResult.success();
            }
            return HttpResult.failed(ResultCode.DELETE_FAILED);
        }catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e);
        }
    }

    @PutMapping
    @ApiOperation("【编辑角色】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Role", value = "角色")
    })
    public HttpResult<?> update(@RequestBody Role role) {
        try {
            if (roleService.count(new QueryWrapper<Role>().eq("authority", role.getAuthority())) > 0) {
                return HttpResult.failed(ResultCode.ROLE_IS_EXIST);
            }
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
    @ApiOperation("【查询角色列表】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true),
            @ApiImplicitParam(name = "pageSize", value = "一页数量", required = true),
            @ApiImplicitParam(name = "roleName", value = "角色名称"),
            @ApiImplicitParam(name = "authority", value = "角色编码")
    })
    public HttpResult<?> list( @RequestParam(name="currentPage", defaultValue="1") Integer currentPage,
                               @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                               @RequestParam(name = "authority",required = false) String authority,
                               @RequestParam(name = "roleName",required = false) String roleName) {
        try{
            QueryWrapper<Role> wrapper = new QueryWrapper<>();
            if (StringUtils.isNotBlank(roleName)) {
                wrapper.like("role_name", roleName);
            }
            if (StringUtils.isNotBlank(authority)) {
                wrapper.like("authority", authority);
            }
           return HttpResult.success(roleService.page(new Page<>(currentPage, pageSize), wrapper));
        }catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e);
        }
    }


    @GetMapping("/checkRoleCode")
    @ApiOperation("【校验角色编码是否唯一】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authority", value = "角色名称",dataType = "string")
    })
    public HttpResult<?> checkRoleCode(@RequestParam(name="roleCode") String roleCode) {
        try{
            return HttpResult.success(!(roleService.count(new QueryWrapper<Role>().eq("authority", roleCode)) > 0));
        }catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e);
        }
    }


    @PostMapping("/saveRolePermission")
    @ApiOperation("【授权相关的菜单给角色】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authority", value = "角色名称",dataType = "string")
    })
    public HttpResult<?> saveRolePermission(@RequestBody RolePermissionVo rolePermissionVo) {
        try{
            return rolePermissionService.saveRolePermission(rolePermissionVo);
        }catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e);
        }
    }

}
