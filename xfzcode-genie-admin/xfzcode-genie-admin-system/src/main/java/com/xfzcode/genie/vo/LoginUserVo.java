package com.xfzcode.genie.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xfzcode.genie.base.BaseEntity;
import com.xfzcode.genie.utils.DateTimeUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class LoginUserVo extends BaseEntity implements Serializable {

    /**
     * 登录账号
     */
    @ApiModelProperty(value = "账号")
    private String userName;

    @JsonIgnore
    @ApiModelProperty(value = "密码")
    private String password;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date accountExpireDate = DateTimeUtils.getMaxDate();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date credentialExpireDate = DateTimeUtils.getMaxDate();

    @Builder.Default
    @JSONField(serialize = true)
    private boolean accountNonLocked = true;

    @Builder.Default
    @JSONField(serialize = true)
    @ApiModelProperty(value = "启用状态")
    private boolean enabled = true;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "姓名")
    private String realName;
    @ApiModelProperty(value = "头像")
    private String avatar;

    private String token;
}
