package com.niveksys.mvcrest.mapper;

import com.niveksys.mvcrest.dto.CategoryDto;
import com.niveksys.mvcrest.model.Category;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto categoryToCategoryDto(Category category);

}
