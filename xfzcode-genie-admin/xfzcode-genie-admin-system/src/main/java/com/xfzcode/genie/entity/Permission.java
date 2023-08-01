package com.xfzcode.genie.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xfzcode.genie.base.BaseEntity;
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
public class Permission extends BaseEntity implements Serializable {

    /**
     * 菜单标题
     */
    private String name;
    /**
     * 路径
     */
    private String url;
    /**
     * 前端组件
     */
    private String component;
    /**
     * 组件名称
     */
    private String componentName;
    /**
     * 菜单类型(0:一级菜单; 1:子菜单:2:按钮权限)
     */
    private Integer menuType;

    /**
     * 菜单状态（0正常 1停用）
     */
    private Integer status;
    /**
     * 菜单权限编码
     */
    private String perms;

    /**
     * 权限策略:1显示2禁用
     */
    private Integer permsType;

    /**
     * 菜单排序
     */
    private Double sortNo;

    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 是否删除（0未删除 1已删除）
     */
    private Integer delFlag;
    /**
     * 父级
     */
    private Long parentId;
    /**
     * 描述
     */
    private String description;

    /**
     * 是否叶子节点:1是 0否
     */
    private Integer isLeaf;

}
