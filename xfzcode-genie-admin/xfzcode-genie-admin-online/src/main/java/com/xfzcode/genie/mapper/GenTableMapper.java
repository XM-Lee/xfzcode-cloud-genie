package com.xfzcode.genie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xfzcode.genie.entity.GenTable;
import com.xfzcode.genie.dto.GenTableDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: XMLee
 * @Date: 2023/8/29 10:30
 * @Description:
 */
@Mapper
public interface GenTableMapper extends BaseMapper<GenTable> {
    GenTableDTO selectGenTableByName(String tableName);
}
