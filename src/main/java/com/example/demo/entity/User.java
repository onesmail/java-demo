package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/*
 * @description: 用户实体类
 */
@Data
@AllArgsConstructor // 自动生成一个无参构造方法
@NoArgsConstructor // 自动为该类生成一个包含所有字段的构造器
@Accessors(chain = true) // 通过链式调用设置属性的值
@TableName("user") // 指定实体类对应的表名
public class User {
    @TableId // 指定为主键，我们配置的是auto，即主键自增策略
    private Long id;

    private String username;

    /*
     * 年龄
     */
    private Integer age;

    /*
     * 创建时间
     * 指定该值使用我们的自动填充策略，INSERT为插入时填充，INSERT_UPDATE为插入和更新时填充
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /*
     * 更新时间
     * 指定该值使用我们的自动填充策略，INSERT为插入时填充，INSERT_UPDATE为插入和更新时填充
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /*
     * 逻辑删除
     */
    @TableLogic
    private Integer deleted;
}
