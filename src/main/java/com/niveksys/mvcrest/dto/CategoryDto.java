package com.niveksys.mvcrest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CategoryDto {

    private Long id;

    @ApiModelProperty(value = "Name", required = true)
    private String name;

}
