package com.example.demo.entity.dto;

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

    @ApiModelProperty(value = "姓名", required = true, example = "tom")
    private String username;

    @ApiModelProperty(value = "年龄", required = true, example = "18")
    private Integer age;
}
