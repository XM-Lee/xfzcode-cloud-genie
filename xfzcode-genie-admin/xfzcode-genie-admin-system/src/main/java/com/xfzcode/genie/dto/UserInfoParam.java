package com.xfzcode.genie.dto;

import lombok.Data;

/**
 * @Author: XMLee
 * @Date: 2023/7/25 11:19
 * @Description:
 */
@Data
public class UserInfoParam {
    private String username;
    private String password;
    private String enabled;
    private String phone;
    private String realName;

}
