package com.xfzcode.genie.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xfzcode.genie.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@TableName("sys_roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="角色对象",description="角色相关")
public class Role extends BaseEntity implements Serializable {
    /**
     * 描述
     */
    @ApiModelProperty(value="描述",name="description",required = false)
    private String description;
    /**
     * 角色名称
     */
    @ApiModelProperty(value="角色名称",name="roleName",required = true)
    private String roleName;
    /**
     * 角色编码
     */
    @ApiModelProperty(value="角色编码",name="authority",required = true)
    private String authority;
}
