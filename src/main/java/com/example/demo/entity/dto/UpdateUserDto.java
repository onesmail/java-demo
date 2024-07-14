package com.example.demo.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/*
 * @Description: 更新用户数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("更新用户数据")
public class UpdateUserDto {

    @ApiModelProperty(value = "编号", required = true, example = "tom")
    private Long id;

    @ApiModelProperty(value = "姓名", required = true, example = "tom")
    private String username;

    @ApiModelProperty(value = "年龄", required = true, example = "tom")
    private Integer age;
}
