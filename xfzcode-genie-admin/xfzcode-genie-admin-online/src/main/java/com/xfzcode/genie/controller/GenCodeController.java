package com.xfzcode.genie.controller;

import com.xfzcode.genie.constant.ApiVersion;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: XMLee
 * @Date: 2023/8/29 15:53
 * @Description:
 */
@RestController
@RequestMapping(ApiVersion.V1_GenCode)
@Slf4j
@Api(value = "代码生成相关API", tags = " 01-01.代码生成相关API-GenCodeController")
public class GenCodeController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
