package com.xfzcode.genie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: XMLee
 * @Date: 2023/7/24 11:39
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GenieAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(GenieAdminApplication.class, args);
    }
}
