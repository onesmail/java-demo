package com.example.demo.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
 * @Description: 分页结果数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("分页结果数据")
public class PageResult<T> {

    /*
     * 分页数据
     */
    @ApiModelProperty(value = "分页数据")
    private List<T> result;

    /*
     * 总条数
     */
    @ApiModelProperty(value = "总条数")
    private Long total;
}

