package com.example.demo.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/*
 * @Description: 配置 Mybatis Plus 自动填充来实现对create_time、update_time的值自动插入和更新
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入填充方法，用于在插入数据时自动为指定的字段设置当前时间。
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 为"createTime"字段设置当前时间
        setFieldValByName("createTime", new Date(), metaObject);
        // 为"updateTime"字段设置当前时间
        setFieldValByName("updateTime", new Date(), metaObject);
        // 为"deleted"字段设置默认未删除
        setFieldValByName("deleted", 0, metaObject);
    }

    /**
     * 更新实体对象的填充时间戳。
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // 设置字段"updateTime"的值为当前时间，实现自动更新时间戳的功能。
        setFieldValByName("updateTime", new Date(), metaObject);
    }
}
