package com.xfzcode.genie.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: XMLee
 * @Date: 2023/8/8 10:36
 * @Description:
 */
@Data
@ApiModel(value="登录对象", description="登录对象")
public class LoginModel implements Serializable {
    @ApiModelProperty(value = "账号【手机或者其他】")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "验证码")
    private String captcha;
    @ApiModelProperty(value = "验证码key")
    private String checkKey;
}
