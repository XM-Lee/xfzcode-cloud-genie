package com.xfzcode.genie.controller;

import com.xfzcode.genie.constant.ApiVersion;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: XMLee
 * @Date: 2023/4/20 9:51
 * @Description:
 */
@RestController
@RequestMapping(ApiVersion.V1_ROLE)
@RequiredArgsConstructor
@Api(value = "01. Role 角色管理",tags = "角色管理相关接口-RoleController")
public class RoleController {

}
