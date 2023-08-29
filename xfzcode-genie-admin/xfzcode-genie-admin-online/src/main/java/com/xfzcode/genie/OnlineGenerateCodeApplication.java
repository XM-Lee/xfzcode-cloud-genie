package com.xfzcode.genie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: XMLee
 * @Date: 2023/8/25 17:37
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OnlineGenerateCodeApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineGenerateCodeApplication.class, args);
    }
}
