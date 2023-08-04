package com.xfzcode.genie.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: XMLee
 * @Date: 2023/8/3 15:38
 * @Description:
 */
@Data
@ApiModel(value="角色权限实体",description="角色权限实体相关")
public class RolePermissionVo {
    @ApiModelProperty(value="角色Id",name="roleId")
    private Long roleId;
    @ApiModelProperty(value="权限Id列表",name="permissionIds")
    private List<Long> permissionIds;
}
