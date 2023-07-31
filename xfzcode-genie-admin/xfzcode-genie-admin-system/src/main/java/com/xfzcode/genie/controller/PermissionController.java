package com.xfzcode.genie.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xfzcode.genie.api.HttpResult;
import com.xfzcode.genie.constant.ApiVersion;
import com.xfzcode.genie.entity.Permission;
import com.xfzcode.genie.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: XMLee
 * @Date: 2023/4/24 11:29
 * @Description:
 */
@RestController
@RequestMapping(ApiVersion.V1_PERMISSION)
@RequiredArgsConstructor
@Api(value = "01. Permission 权限管理",tags = "权限管理相关接口-PermissionController")
public class PermissionController {


    @Autowired
    private PermissionService permissionService;


    @PostMapping()
    @ApiOperation(value = "新增权限", notes = "新增权限")
    public HttpResult<?> addPermission(@RequestBody Permission permission) {
        return HttpResult.success(permissionService.save(permission));
    }

    @DeleteMapping
    @ApiOperation(value = "删除权限", notes = "删除权限")
    public HttpResult<?> deletePermission(@RequestBody List<Long> ids) {
        return HttpResult.success(permissionService.removeBatchByIds(ids));
    }

    @PutMapping
    @ApiOperation(value = "编辑权限", notes = "编辑权限")
    public HttpResult<?> updatePermission(@RequestBody Permission permission) {
        return HttpResult.success(permissionService.updateById(permission));
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "编辑权限", notes = "编辑权限")
    public HttpResult<?> detail(@PathVariable String id) {
        return HttpResult.success(permissionService.getById(id));
    }
    @GetMapping("/checkPermDuplication")
    @ApiOperation(value = "查询权限关键数据是否有重复", notes = "查询权限关键数据是否有重复")
    public HttpResult<?> checkPermDuplication(@RequestParam String name) {
        if (permissionService.list(new QueryWrapper<Permission>().eq("name", name)).size() > 0) {
            return HttpResult.failed();
        }
        return HttpResult.success();
    }


    @GetMapping
    @ApiOperation(value = "权限", notes = "编辑权限")
    public HttpResult<?> list(@PathVariable String id) {
        return HttpResult.success(permissionService.getById(id));
    }


    @GetMapping("/list")
    public HttpResult<?> getRoleListAll() {
        return HttpResult.success(permissionService.list());
    }

}
