package com.xfzcode.genie.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: XMLee
 * @Date: 2023/8/8 14:41
 * @Description:
 */
@Data
public class RegisterUser implements Serializable {
    private String phone;
    private String code;
    private String password;
    private String rePassword;
}
