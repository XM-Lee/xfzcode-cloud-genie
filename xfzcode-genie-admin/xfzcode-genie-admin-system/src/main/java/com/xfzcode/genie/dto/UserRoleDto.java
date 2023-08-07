package com.xfzcode.genie.dto;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @Author: XMLee
 * @Date: 2023/8/7 17:50
 * @Description:
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDto {
    private Long userId;
    private Long id;
    /**
     * 描述
     */
    private String description;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色标识
     */
    private String authority;
    private LocalDateTime createTimestamp;
    private LocalDateTime updateTimestamp;
}
