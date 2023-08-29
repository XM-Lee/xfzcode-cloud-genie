package com.xfzcode.genie;

import com.xfzcode.genie.dto.GenTableDTO;
import com.xfzcode.genie.entity.*;
import com.xfzcode.genie.mapper.GenTableMapper;
import com.xfzcode.genie.utils.StringUtils;
import com.xfzcode.genie.utils.VelocityInitializer;
import com.xfzcode.genie.utils.VelocityUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.StringWriter;
import java.util.List;

/**
 * @Author: XMLee
 * @Date: 2023/8/28 18:27
 * @Description:
 */
@SpringBootTest
public class testGen {

    @Autowired
    private GenTableMapper genTableMapper;

    @Test
    public void testTem() {
        try {
        // 查询表信息
        GenTableDTO table = genTableMapper.selectGenTableByName("test_users");
        // 设置主子表信息
        //setSubTable(table);
        // 设置主键列信息
        setPkColumn(table);

        VelocityInitializer.initVelocity();

        VelocityContext context = VelocityUtils.prepareContext(table);
            List<String> templateList = VelocityUtils.getTemplateList(table.getTplCategory());
            for (String s : templateList) {
                StringWriter sw = new StringWriter();
                Template tpl = Velocity.getTemplate(s, "UTF-8");
                tpl.merge(context, sw);
                System.out.println(sw.toString());
                System.out.println("======================================================");
            }

            // 获取模板列表



            /*List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
            for (String template : templates) {
                // 渲染模板
                StringWriter sw = new StringWriter();
                Template tpl = Velocity.getTemplate(template, "UTF-8");
                tpl.merge(context, sw);
                System.out.println(sw.toString());

            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPkColumn(GenTableDTO table) {
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
