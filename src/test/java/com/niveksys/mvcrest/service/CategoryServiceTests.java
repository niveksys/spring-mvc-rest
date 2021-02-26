package com.niveksys.mvcrest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.niveksys.mvcrest.dto.CategoryDto;
import com.niveksys.mvcrest.mapper.CategoryMapper;
import com.niveksys.mvcrest.model.Category;
import com.niveksys.mvcrest.repository.CategoryRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTests {
    public static final Long ID = 1L;
    public static final String NAME = "Fruits";

    @Mock
    CategoryRepository categoryRepository;

    CategoryService categoryService;

    @BeforeEach
    public void setUp() throws Exception {
        this.categoryService = new CategoryServiceImpl(this.categoryRepository, CategoryMapper.INSTANCE);
    }

    @Test
    public void findAll() throws Exception {
        // given
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());
        when(this.categoryRepository.findAll()).thenReturn(categories);

        // when
        List<CategoryDto> categoryDtoList = categoryService.findAll();

        // then
        assertEquals(3, categoryDtoList.size());
    }

    @Test
    public void findByName() throws Exception {
        // given
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        when(this.categoryRepository.findByNameIgnoreCase(anyString())).thenReturn(Optional.of(category));

        // when
        CategoryDto categoryDto = categoryService.findByName(NAME);

        // then
        assertEquals(ID, categoryDto.getId());
        assertEquals(NAME, categoryDto.getName());
    }
}
