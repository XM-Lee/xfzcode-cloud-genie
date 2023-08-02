package com.xfzcode.genie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xfzcode.genie.api.HttpResult;
import com.xfzcode.genie.api.ResultCode;
import com.xfzcode.genie.constant.ApiVersion;
import com.xfzcode.genie.constant.CommonConstant;
import com.xfzcode.genie.entity.Permission;
import com.xfzcode.genie.service.PermissionService;
import com.xfzcode.genie.vo.PermissionTree;
import io.swagger.annotations.Api;
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
@Api(value = "01. Permission 权限管理",tags = "权限管理相关接口-PermissionController")
public class PermissionController {


    @Autowired
    private PermissionService permissionService;


    @PostMapping()
    @ApiOperation(value = "新增权限", notes = "新增权限")
    public HttpResult<?> addPermission(@RequestBody Permission permission) {
        //TODO 需要校验某些字段唯一性
        return HttpResult.success(permissionService.save(permission));
    }

    @DeleteMapping
    @ApiOperation(value = "删除权限", notes = "删除权限")
    public HttpResult<?> deletePermission(@RequestBody List<Long> ids) {
        //TODO 需要校验该权限是否被使用着或级联删除
        return HttpResult.success(permissionService.removeBatchByIds(ids));
    }

    @PutMapping
    @ApiOperation(value = "编辑权限", notes = "编辑权限")
    public HttpResult<?> updatePermission(@RequestBody Permission permission) {
        //TODO 需要校验某些字段唯一性
        return HttpResult.success(permissionService.updateById(permission));
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "权限详情", notes = "权限详情")
    public HttpResult<?> detail(@PathVariable String id) {
        return HttpResult.success(permissionService.getById(id));
    }



    @GetMapping("/list")
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
