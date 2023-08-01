package com.xfzcode.genie.vo;

import com.xfzcode.genie.entity.Permission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: XMLee
 * @Date: 2023/8/1 18:26
 * @Description:
 */
@Getter
@Setter
public class PermissionTree extends Permission {
    private List<PermissionTree> children;
}
