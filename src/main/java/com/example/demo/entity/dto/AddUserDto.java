package com.example.demo.entity.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("添加用户数据")
public class AddUserDto {

    @ExcelProperty("姓名")
    @ColumnWidth(20)
    @ApiModelProperty(value = "姓名", required = true, example = "tom")
    private String userName;

    @ExcelProperty("年龄")
    @ColumnWidth(20)
    @ApiModelProperty(value = "年龄", required = true, example = "18")
    private Integer age;
}
