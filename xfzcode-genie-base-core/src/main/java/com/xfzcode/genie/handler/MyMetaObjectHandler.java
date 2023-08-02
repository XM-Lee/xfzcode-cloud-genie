package com.xfzcode.genie.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: XMLee
 * @Date: 2023/7/25 9:37
 * @Description:
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    //当mp实现添加操作的时候，这个方法执行
    @Override
    public void insertFill(MetaObject metaObject) {
        System.out.println("=========================================================");
        /*this.setFieldValByName("create_time", new Date(), metaObject);
        this.setFieldValByName("update_time", new Date(), metaObject);*/
        this.strictInsertFill(metaObject, "createTime", LocalDateTime:: now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime:: now, LocalDateTime.class);
    }

    //当mp实现修改操作的时候，这个方法执行
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject,"updateTime",LocalDateTime::now, LocalDateTime.class);
    }
}
