package com.xfzcode.genie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xfzcode.genie.entity.GenTableColumn;
import com.xfzcode.genie.mapper.GenTableColumnMapper;
import com.xfzcode.genie.service.GenTableColumnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: XMLee
 * @Date: 2023/8/29 18:37
 * @Description:
 */
@Service
@Slf4j
public class GenTableColumnServiceImpl extends ServiceImpl<GenTableColumnMapper,GenTableColumn> implements GenTableColumnService {
}
