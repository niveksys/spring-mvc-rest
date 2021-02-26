package com.niveksys.mvcrest.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CatorgoryListDto {

    List<CategoryDto> categories;

}
