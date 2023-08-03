package com.xfzcode.genie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xfzcode.genie.api.HttpResult;
import com.xfzcode.genie.api.ResultCode;
import com.xfzcode.genie.constant.ApiVersion;
import com.xfzcode.genie.constant.CommonConstant;
import com.xfzcode.genie.entity.Permission;
import com.xfzcode.genie.entity.Role;
import com.xfzcode.genie.service.PermissionService;
import com.xfzcode.genie.vo.PermissionTree;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kotlin.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: XMLee
 * @Date: 2023/4/24 11:29
 * @Description:
 */
@RestController
@RequestMapping(ApiVersion.V1_PERMISSION)
@RequiredArgsConstructor
@Slf4j
@Api(value = "Permission 权限管理",tags = "1-03.权限管理相关接口-PermissionController")
public class PermissionController {


    @Autowired
    private PermissionService permissionService;


    @PostMapping()
    @ApiOperation("【新增权限】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Permission", value = "权限")
    })
    public HttpResult<?> addPermission(@RequestBody Permission permission) {
        try {
            if (permissionService.count(new QueryWrapper<Permission>().eq("url", permission.getUrl())) > 0) {
                return HttpResult.failed(ResultCode.PERMISSION_IS_EXIST);
            }
            if (permissionService.save(permission)) {
                return HttpResult.success();
            }
            return HttpResult.failed(ResultCode.DELETE_FAILED);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e);
        }
    }

    @DeleteMapping
    @ApiOperation("【删除权限】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Role", value = "角色")
    })
    public HttpResult<?> deletePermission(@RequestBody List<Long> ids) {
        try {
            if (permissionService.removePermissionByIds(ids)) {
                return HttpResult.success();
            }
            return HttpResult.failed(ResultCode.DELETE_FAILED);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e);
        }
    }

    @PutMapping
    @ApiOperation("【编辑权限】")
    public HttpResult<?> updatePermission(@RequestBody Permission permission) {
        try {
            if (permissionService.count(new QueryWrapper<Permission>().eq("url", permission.getUrl())) > 0) {
                return HttpResult.failed(ResultCode.PERMISSION_IS_EXIST);
            }
            return HttpResult.success(permissionService.updateById(permission));
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e);
        }
    }


    @GetMapping("/{id}")
    @ApiOperation("【权限详情】")
    public HttpResult<?> detail(@PathVariable String id) {
        try {
            return HttpResult.success(permissionService.getById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e);
        }
    }



    @GetMapping("/list")
    @ApiOperation("【权限列表】")
    public HttpResult<?> list(@RequestParam(required = false) String permissionName) {
        long start = System.currentTimeMillis();
        try {
            LambdaQueryWrapper<Permission> query = new LambdaQueryWrapper<Permission>();
            query.eq(Permission::getDelFlag, CommonConstant.DEL_FLAG_0);
            query.orderByAsc(Permission::getSortNo);
            //支持通过菜单名字，模糊查询
            if(StringUtils.isNotEmpty(permissionName)){
                query.like(Permission::getName, permissionName);
            }
            List<Permission> list = permissionService.list(query);
            if (null != list  && list.size() > 0) {
                List<PermissionTree> treeList = list.stream().map(a->{
                    PermissionTree permissionTree = new PermissionTree();
                    BeanUtils.copyProperties(a, permissionTree);
                    return permissionTree;
                }).collect(Collectors.toList());


                List<PermissionTree> trees = treeList.stream().filter(permissionTree -> 0 == permissionTree.getParentId())
                        .peek(permissionTree -> permissionTree.setChildren(createChildList(permissionTree, treeList)))
                        .sorted(Comparator.comparing(PermissionTree::getSortNo).reversed())
                        .collect(Collectors.toList());

                log.info("======获取全部菜单数据=====耗时:" + (System.currentTimeMillis() - start) + "毫秒");
                return HttpResult.success(trees);
            }
            return HttpResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            return HttpResult.failed(ResultCode.FAILED);
        }
    }


    private static List<PermissionTree> createChildList(PermissionTree permissionTreeVo, List<PermissionTree> permissionTreeVoList) {
        return permissionTreeVoList.stream().filter(model -> permissionTreeVo.getId().equals(model.getParentId()))
                .peek(model -> model.setChildren(createChildList(model, permissionTreeVoList)))
                .sorted((Comparator.comparing(PermissionTree::getSortNo).reversed())).collect(Collectors.toList());
    }

}
