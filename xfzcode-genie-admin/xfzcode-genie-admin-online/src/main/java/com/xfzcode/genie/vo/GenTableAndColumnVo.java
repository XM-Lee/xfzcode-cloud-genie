package com.xfzcode.genie.vo;

import com.xfzcode.genie.entity.GenTable;
import com.xfzcode.genie.entity.GenTableColumn;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: XMLee
 * @Date: 2023/8/29 18:30
 * @Description:
 */
@Data
public class GenTableAndColumnVo implements Serializable {
    private GenTable genTable;
    private List<GenTableColumn> genTableColumnList;


}
