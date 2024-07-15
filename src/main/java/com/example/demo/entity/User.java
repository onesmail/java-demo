package com.example.demo.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 用户实体类
 */
@Data
@AllArgsConstructor // 自动生成一个无参构造方法
@NoArgsConstructor // 自动为该类生成一个包含所有字段的构造器
@Accessors(chain = true) // 通过链式调用设置属性的值
@TableName("user") // 指定实体类对应的表名
public class User {

    @TableId // 指定为主键，我们配置的是auto，即主键自增策略
    @ExcelIgnore
    @ApiModelProperty(value = "编号")
    private Long id;

    /**
     * 用户名
     */
    @ExcelProperty("姓名")
    @ColumnWidth(20)
    @ApiModelProperty(value = "姓名")
    private String username;

    /**
     * 年龄
     */
    @ExcelProperty("年龄")
    @ColumnWidth(20)
    @ApiModelProperty(value = "年龄")
    private Integer age;

    /**
     * 创建时间
     * 指定该值使用我们的自动填充策略，INSERT为插入时填充
     */
    @ExcelProperty("创建时间")
    @ColumnWidth(20)
    @DateTimeFormat("yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     * 指定该值使用我们的自动填充策略，INSERT_UPDATE为插入和更新时填充
     */
    @ExcelIgnore
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 逻辑删除
     */
    @ExcelIgnore
    @TableLogic
    @ApiModelProperty(value = "逻辑删除")
    private Integer deleted;
}
