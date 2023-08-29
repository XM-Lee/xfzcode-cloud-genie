package com.xfzcode.genie.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: XMLee
 * @Date: 2023/7/25 10:30
 * @Description:
 */
public class ApiVersion {
    public static final String V1 = "/v1";
    public static final String V1_GenCode = V1 + "/genCode";

    // 对于白名单中的URL，不检查JWT和鉴权
    public static final List<String> ANT_WHITE_LIST = new ArrayList<>();


    static {
        /*swagger ui*/
        ANT_WHITE_LIST.add("/swagger-ui/**");
        ANT_WHITE_LIST.add("/swagger-resources/**");
        ANT_WHITE_LIST.add("/swagger.*");
        ANT_WHITE_LIST.add("/v3/**");

//        ANT_WHITE_LIST.add(V1_GenCode+"/**");

    }
}
