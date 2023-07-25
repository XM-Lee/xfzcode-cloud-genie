package com.xfzcode.genie.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xfzcode.genie.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@TableName("sys_role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity implements Serializable {
    /**
     * 描述
     */
    private String description;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色标识
     */
    private String authority;
}
