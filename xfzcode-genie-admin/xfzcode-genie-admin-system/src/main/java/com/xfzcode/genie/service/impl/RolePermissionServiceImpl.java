package com.xfzcode.genie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xfzcode.genie.api.HttpResult;
import com.xfzcode.genie.api.ResultCode;
import com.xfzcode.genie.entity.RolePermission;
import com.xfzcode.genie.mapper.RolePermissionMapper;
import com.xfzcode.genie.service.RolePermissionService;
import com.xfzcode.genie.vo.RolePermissionVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: XMLee
 * @Date: 2023/8/3 15:42
 * @Description:
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper,RolePermission> implements RolePermissionService {


    @Override
    @Transactional
    public HttpResult<?> saveRolePermission(RolePermissionVo rolePermissionVo) {
        this.baseMapper.delete(new QueryWrapper<RolePermission>().eq("role_id", rolePermissionVo.getRoleId()));
        List<RolePermission> rolePermissions = rolePermissionVo.getPermissionIds().stream().map(a -> new RolePermission(rolePermissionVo.getRoleId(), a)).collect(Collectors.toList());
        if (this.saveBatch(rolePermissions)) {
            return HttpResult.success();
        }
        return HttpResult.failed(ResultCode.SAVE_FAILED);
    }

}
