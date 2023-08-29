package com.xfzcode.genie.constants;

/**
 * @Author: XMLee
 * @Date: 2023/8/29 14:46
 * @Description:
 */
public interface GenConstants {
    /** 单表（增删改查） */
    public static final String TPL_CRUD = "crud";
    /** 树表（增删改查） */
    public static final String TPL_TREE = "tree";
    /** 树名称字段 */
    public static final String TREE_NAME = "treeName";
    /** 树编码字段 */
    public static final String TREE_CODE = "treeCode";
    /** 主子表（增删改查） */
    public static final String TPL_SUB = "sub";
    /** 树父编码字段 */
    public static final String TREE_PARENT_CODE = "treeParentCode";

    /** Entity基类字段 */
    public static final String[] BASE_ENTITY = {"id", "createBy", "createTime", "updateBy", "updateTime", "remark" };

    /** Tree基类字段 */
    public static final String[] TREE_ENTITY = { "parentName", "parentId", "orderNum", "ancestors" };

    /** 高精度计算类型 */
    public static final String TYPE_BIGDECIMAL = "BigDecimal";

    /** 时间类型 */
    public static final String TYPE_DATE = "Date";
    /** 上级菜单ID字段 */
    public static final String PARENT_MENU_ID = "parentMenuId";
}
