package com.xfzcode.genie.vo;

import com.xfzcode.genie.dto.UserRoleDto;
import com.xfzcode.genie.entity.User;
import lombok.Data;

import java.util.List;

/**
 * @Author: XMLee
 * @Date: 2023/8/7 17:54
 * @Description:
 */
@Data
public class UserRoleVo {
    private User user;
    private List<UserRoleDto> userRoleList;
}
