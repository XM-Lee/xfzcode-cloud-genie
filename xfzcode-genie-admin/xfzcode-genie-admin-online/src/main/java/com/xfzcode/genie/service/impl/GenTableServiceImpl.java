package com.xfzcode.genie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xfzcode.genie.api.HttpResult;
import com.xfzcode.genie.api.ResultCode;
import com.xfzcode.genie.dto.GenTableDTO;
import com.xfzcode.genie.entity.GenTable;
import com.xfzcode.genie.entity.GenTableColumn;
import com.xfzcode.genie.mapper.GenTableMapper;
import com.xfzcode.genie.service.GenTableColumnService;
import com.xfzcode.genie.service.GenTableService;
import com.xfzcode.genie.utils.StringUtils;
import com.xfzcode.genie.utils.VelocityInitializer;
import com.xfzcode.genie.utils.VelocityUtils;
import com.xfzcode.genie.vo.GenTableAndColumnVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Author: XMLee
 * @Date: 2023/8/29 18:33
 * @Description:
 */
@Service
@Slf4j
public class GenTableServiceImpl extends ServiceImpl<GenTableMapper, GenTable> implements GenTableService {

    @Autowired
    public GenTableColumnService genTableColumnService;

    @Autowired
    private GenTableMapper genTableMapper;

    @Override
    @Transactional
    public HttpResult<?> insertGenTableAndColumn(GenTableAndColumnVo genTableAndColumnVo) {
        GenTable genTable = genTableAndColumnVo.getGenTable();
        if (this.save(genTable)) {
            GenTable tableInDb = this.getOne(new QueryWrapper<GenTable>().eq("table_name", genTable.getTableName()));
            List<GenTableColumn> columnList = genTableAndColumnVo.getGenTableColumnList();
            List<GenTableColumn> collect = columnList.stream().peek(a -> a.setTableId(tableInDb.getTableId())).collect(Collectors.toList());
            if (genTableColumnService.saveBatch(collect)) {
                return HttpResult.success();
            }
        }
        return HttpResult.failed();
    }

    @Override
    @Transactional
    public HttpResult<?> updateGenTable(GenTableAndColumnVo genTableAndColumnVo) {
        GenTable genTableInDB = this.getById(genTableAndColumnVo.getGenTable().getTableId());
        if (null == genTableInDB) {
            HttpResult.failed(ResultCode.VALIDATE_FAILED);
        }
        if (this.updateById(genTableAndColumnVo.getGenTable())) {
            List<GenTableColumn> genTableColumnList = genTableAndColumnVo.getGenTableColumnList();
            if (genTableColumnService.saveOrUpdateBatch(genTableColumnList)) {
                return HttpResult.success();
            }
            return HttpResult.failed(ResultCode.UPDATE_FAILED);
        }
        return HttpResult.failed(ResultCode.UPDATE_FAILED);
    }

    @Override
    public byte[] genCodeDownload(String tableName) {
        try {
            // 查询表信息
            GenTableDTO table = genTableMapper.selectGenTableByName(tableName);
            if (null != table) {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ZipOutputStream zip = new ZipOutputStream(outputStream);
                generatorCode(table,zip);
                IOUtils.closeQuietly(zip);
                return outputStream.toByteArray();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void  generatorCode(GenTableDTO table, ZipOutputStream zip) {
        // 设置主键列信息
        setPkColumn(table);
        VelocityInitializer.initVelocity();
        VelocityContext context = VelocityUtils.prepareContext(table);
        List<String> templateList = VelocityUtils.getTemplateList(table.getTplCategory());
        for (String template : templateList) {
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);
            try {
                // 添加到zip
                zip.putNextEntry(new ZipEntry(VelocityUtils.getFileName(template, table)));
                IOUtils.write(sw.toString(), zip, "UTF-8");
                IOUtils.closeQuietly(sw);
                zip.flush();
                zip.closeEntry();
            } catch (IOException e) {
                log.error("渲染模板失败，表名：" + table.getTableName(), e);
            }
        }

    }

    private void setPkColumn(GenTableDTO table) {
        for (GenTableColumn column : table.getColumns()) {
            if (column.getIsPk()) {
                table.setPkColumn(column);
                break;
            }
        }
        if (StringUtils.isNull(table.getPkColumn())) {
            table.setPkColumn(table.getColumns().get(0));
        }
        if ("sub".equals(table.getTplCategory())) {
            for (GenTableColumn column : table.getSubTable().getColumns()) {
                if (column.getIsPk()) {
                    table.getSubTable().setPkColumn(column);
                    break;
                }
            }
            if (StringUtils.isNull(table.getSubTable().getPkColumn())) {
                table.getSubTable().setPkColumn(table.getSubTable().getColumns().get(0));
            }
        }
    }
}
