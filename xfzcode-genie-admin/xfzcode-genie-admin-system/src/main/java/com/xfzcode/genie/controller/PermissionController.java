package com.xfzcode.genie.controller;

import com.xfzcode.genie.constant.ApiVersion;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: XMLee
 * @Date: 2023/4/24 11:29
 * @Description:
 */
@RestController
@RequestMapping(ApiVersion.V1_PERMISSION)
@RequiredArgsConstructor
@Api(value = "01. Permission 权限管理",tags = "权限管理相关接口-PermissionController")
public class PermissionController {


}
