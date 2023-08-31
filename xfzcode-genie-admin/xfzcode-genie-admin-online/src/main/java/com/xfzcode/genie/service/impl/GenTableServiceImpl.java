package com.xfzcode.genie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xfzcode.genie.api.HttpResult;
import com.xfzcode.genie.api.ResultCode;
import com.xfzcode.genie.entity.GenTable;
import com.xfzcode.genie.entity.GenTableColumn;
import com.xfzcode.genie.mapper.GenTableMapper;
import com.xfzcode.genie.service.GenTableColumnService;
import com.xfzcode.genie.service.GenTableService;
import com.xfzcode.genie.vo.GenTableAndColumnVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
}
