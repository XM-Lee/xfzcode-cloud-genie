package com.xfzcode.genie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xfzcode.genie.api.HttpResult;
import com.xfzcode.genie.entity.GenTable;
import com.xfzcode.genie.vo.GenTableAndColumnVo;

/**
 * @Author: XMLee
 * @Date: 2023/8/29 18:32
 * @Description:
 */
public interface GenTableService extends IService<GenTable> {
    HttpResult<?> insertGenTableAndColumn(GenTableAndColumnVo genTableAndColumnVo);
}
