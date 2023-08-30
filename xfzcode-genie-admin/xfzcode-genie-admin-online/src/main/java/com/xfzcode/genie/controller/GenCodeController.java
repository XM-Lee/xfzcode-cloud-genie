package com.xfzcode.genie.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xfzcode.genie.api.HttpResult;
import com.xfzcode.genie.api.ResultMessage;
import com.xfzcode.genie.constant.ApiVersion;
import com.xfzcode.genie.entity.GenTable;
import com.xfzcode.genie.service.GenTableService;
import com.xfzcode.genie.vo.GenTableAndColumnVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private GenTableService genTableService;


    @PostMapping("/addGenCode")
    public HttpResult<?> save(@RequestBody GenTableAndColumnVo genTableAndColumnVo) {
        try{
            if (null != genTableService.getOne(new QueryWrapper<GenTable>().eq("table_name", genTableAndColumnVo.getGenTable().getTableName()))) {
                return HttpResult.failed(ResultMessage.TABLE_NAME_IS_EXIST);
            }
            return genTableService.insertGenTableAndColumn(genTableAndColumnVo);
        }catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e);
        }

    }

}
