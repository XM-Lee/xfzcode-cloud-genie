package com.xfzcode.genie.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xfzcode.genie.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: XMLee
 * @Date: 2023/4/11 11:46
 * @Description:
 */
@TableName(value = "sys_permission")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="权限对象",description="权限相关")
public class Permission extends BaseEntity implements Serializable {

    /**
     * 菜单标题
     */
    @ApiModelProperty(value="菜单标题",name="name")
    private String name;
    /**
     * 路径
     */
    @ApiModelProperty(value="路径",name="url")
    private String url;
    /**
     * 前端组件
     */
    @ApiModelProperty(value="前端组件",name="component")
    private String component;
    /**
     * 组件名称
     */
    @ApiModelProperty(value="组件名称",name="componentName")
    private String componentName;
    /**
     * 菜单类型(0:一级菜单; 1:子菜单:2:按钮权限)
     */
    @ApiModelProperty(value="菜单类型(0:一级菜单; 1:子菜单:2:按钮权限)",name="menuType")
    private Integer menuType;

    /**
     * 菜单状态（0正常 1停用）
     */
    @ApiModelProperty(value="菜单状态（0正常 1停用）",name="status")
    private Integer status;
    /**
     * 菜单权限编码
     */
    @ApiModelProperty(value="菜单权限编码",name="perms")
    private String perms;

    /**
     * 权限策略:1显示2禁用
     */
    @ApiModelProperty(value="权限策略:1显示2禁用",name="permsType")
    private Integer permsType;

    /**
     * 菜单排序
     */
    @ApiModelProperty(value="菜单排序",name="sortNo")
    private Double sortNo;

    /**
     * 菜单图标
     */
    @ApiModelProperty(value="菜单图标",name="icon")
    private String icon;
    /**
     * 是否删除（0未删除 1已删除）
     */
    @ApiModelProperty(value="是否删除0未删除 1已删除）",name="delFlag")
    private Integer delFlag;
    /**
     * 父级
     */
    @ApiModelProperty(value="父级",name="parentId")
    private Long parentId;
    /**
     * 描述
     */
    @ApiModelProperty(value="描述",name="description")
    private String description;

    /**
     * 是否叶子节点:1是 0否
     */
    @ApiModelProperty(value="是否叶子节点:1是 0否",name="isLeaf")
    private Integer isLeaf;

}
