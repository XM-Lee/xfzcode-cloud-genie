package com.xfzcode.genie.constant;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.HttpMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: XMLee
 * @Date: 2023/7/25 10:30
 * @Description:
 */
public class ApiVersion {
    public static final String V1 = "/v1";
    public static final String V1_USER = V1 + "/user";
    public static final String V1_PERMISSION = V1 +"permission";
    public static final String V1_ROLE = V1 +"/role";
    public static final String V1_LOGIN = V1+"/login";

    // 对于白名单中的URL，不检查JWT和鉴权
    public static final List<String> ANT_WHITE_LIST = new ArrayList<>();
    public static final String V1_DEPART = V1 + "/depart";


    static {
        /*swagger ui*/
        ANT_WHITE_LIST.add("/swagger-ui/**");
        ANT_WHITE_LIST.add("/swagger-resources/**");
        ANT_WHITE_LIST.add("/swagger.*");
        ANT_WHITE_LIST.add("/v3/**");

        ANT_WHITE_LIST.add(V1_LOGIN+"/**");
        ANT_WHITE_LIST.add(V1_DEPART+"/**");

    }
}
