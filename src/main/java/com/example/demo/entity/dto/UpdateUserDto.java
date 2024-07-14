package com.example.demo.entity.dto;

import io.swagger.annotations.ApiModel;
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
    private Long id;
    private String username;
    private Integer age;
}
