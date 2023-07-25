package com.xfzcode.genie.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xfzcode.genie.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_users_roles")
public class UserRole extends BaseEntity implements Serializable {

    @TableField("user_id")
    private Long userId;
    @TableField("role_id")
    private Long roleId;
}
