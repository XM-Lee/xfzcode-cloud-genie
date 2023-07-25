package com.xfzcode.genie.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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
        /*this.setFieldValByName("createTimestamp", new Date(), metaObject);
        this.setFieldValByName("updateTimestamp", new Date(), metaObject);*/
        this.strictInsertFill(metaObject, "createTimestamp", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "updateTimestamp", LocalDateTime::now, LocalDateTime.class);
    }

    //当mp实现修改操作的时候，这个方法执行
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject,"updateTimestamp",LocalDateTime::now, LocalDateTime.class);
    }
}
