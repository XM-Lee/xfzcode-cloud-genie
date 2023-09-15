package com.xfzcode.genie.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xfzcode.genie.api.HttpResult;
import com.xfzcode.genie.api.ResultCode;
import com.xfzcode.genie.api.ResultMessage;
import com.xfzcode.genie.constant.ApiVersion;
import com.xfzcode.genie.dto.GenTableDTO;
import com.xfzcode.genie.entity.GenTable;
import com.xfzcode.genie.entity.GenTableColumn;
import com.xfzcode.genie.exception.EntityNotExistException;
import com.xfzcode.genie.service.GenTableColumnService;
import com.xfzcode.genie.service.GenTableService;
import com.xfzcode.genie.vo.GenTableAndColumnVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private GenTableColumnService genTableColumnService;


    @PostMapping("/addGenCode")
    @ApiOperation("【添加代码生成表】")
    public HttpResult<?> addGenCode(@RequestBody GenTableAndColumnVo genTableAndColumnVo) {
        try {
            if (null != genTableService.getOne(new QueryWrapper<GenTable>().eq("table_name", genTableAndColumnVo.getGenTable().getTableName()))) {
                return HttpResult.failed(ResultMessage.TABLE_NAME_IS_EXIST);
            }
            return genTableService.insertGenTableAndColumn(genTableAndColumnVo);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e);
        }
    }

    @DeleteMapping("/deleteGenTable/{tableId}")
    @ApiOperation("【删除代码生成表格】")
    public HttpResult<?> deleteGenTable(@PathVariable("tableId") Long tableId) {
        try {
            if (genTableService.removeById(tableId)) {
                if (genTableColumnService.remove(new QueryWrapper<GenTableColumn>().eq("table_id", tableId))) {
                    return HttpResult.success(ResultCode.SUCCESS);
                }
                log.info("删除table成功，table字段删除失败，table_id={}",tableId);
                return HttpResult.failed(ResultCode.DELETE_FAILED);
            }
            return HttpResult.failed(ResultCode.DELETE_FAILED);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e);
        }
    }


    @PutMapping("/updateGenTable")
    @ApiOperation("【更新代码生成表格】")
    public HttpResult<?> updateGenTable(@RequestBody GenTableAndColumnVo genTableAndColumnVo) {
        try {
            if (null == genTableAndColumnVo.getGenTable().getTableId()) {
                throw new EntityNotExistException();
            }
            return genTableService.updateGenTable(genTableAndColumnVo);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e);
        }
    }


    @GetMapping("/genTableList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true, defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "一页数量", required = true, defaultValue = "10"),
            @ApiImplicitParam(name = "tableName", value = "表名")
    })
    @ApiOperation("【获取在线表单】")
    public HttpResult<?> genTableList(@RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
                                      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                      @RequestParam(name = "tableName", required = false) String tableName) {
        try {
            QueryWrapper<GenTable> wrapper = new QueryWrapper<>();
            if (StringUtils.isNotBlank(tableName)) {
                wrapper.like("table_name", tableName);
            }
            Page<GenTable> page = genTableService.page(new Page<>(currentPage, pageSize), wrapper);
            List<GenTable> records = page.getRecords();
            ArrayList<GenTableDTO> vos = new ArrayList<>();
            if (records.size() > 0) {
                List<Long> tableIds = records.stream().map(GenTable::getTableId).collect(Collectors.toList());
                List<GenTableColumn> tableColumns = genTableColumnService.list(new QueryWrapper<GenTableColumn>().in("table_id", tableIds));
                Map<Long, List<GenTableColumn>> mapByTableId = tableColumns.stream().collect(Collectors.groupingBy(GenTableColumn::getTableId));
                records.forEach(a -> {
                    GenTableDTO dto = new GenTableDTO();
                    BeanUtils.copyProperties(a, dto);
                    dto.setColumns(mapByTableId.getOrDefault(a.getTableId(), mapByTableId.get(a.getTableId())));
                    vos.add(dto);
                });
            }
            return HttpResult.success(vos);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e);
        }
    }

    @GetMapping("/genCodeDownload")
    @ApiImplicitParams({

    })
    @ApiOperation("【生成代码-单表】")
    public void genCodeDownload(HttpServletResponse response,String tableName) {
        try {
            byte[] data = genTableService.genCodeDownload(tableName);
            if (null != data) {
                genCode(response, data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException {
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"xfzcode-genie-code.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}


