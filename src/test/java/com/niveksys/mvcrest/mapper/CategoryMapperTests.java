package com.niveksys.mvcrest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.niveksys.mvcrest.dto.CategoryDto;
import com.niveksys.mvcrest.model.Category;

import org.junit.jupiter.api.Test;

public class CategoryMapperTests {

    public static final String NAME = "Joe";
    public static final long ID = 1L;

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTO() throws Exception {
        // given
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        // when
        CategoryDto categoryDto = categoryMapper.categoryToCategoryDto(category);

        // then
        assertEquals(NAME, categoryDto.getName());
        assertEquals(Long.valueOf(ID), categoryDto.getId());
    }

}
