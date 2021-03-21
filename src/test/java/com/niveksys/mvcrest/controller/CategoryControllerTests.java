package com.niveksys.mvcrest.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import com.niveksys.mvcrest.dto.CategoryDto;
import com.niveksys.mvcrest.service.CategoryService;
import com.niveksys.mvcrest.service.ResourceNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTests {

    public static final String NAME1 = "Fruits";
    public static final String NAME2 = "Dried";

    @MockBean
    CategoryService categoryService;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void listCategories() throws Exception {
        // given
        CategoryDto category1 = new CategoryDto();
        category1.setId(1L);
        category1.setName(NAME1);

        CategoryDto category2 = new CategoryDto();
        category2.setId(2L);
        category2.setName(NAME2);

        List<CategoryDto> categories = Arrays.asList(category1, category2);

        when(this.categoryService.listCategories()).thenReturn(categories);

        // when
        this.mockMvc.perform(get(CategoryController.BASE_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.categories", hasSize(2)));
    }

    @Test
    public void showCategoryByName() throws Exception {
        // given
        CategoryDto category = new CategoryDto();
        category.setId(1L);
        category.setName(NAME1);

        when(this.categoryService.getCategoryByName(anyString())).thenReturn(category);

        // then
        mockMvc.perform(get(CategoryController.BASE_URL + "/" + NAME1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.name", equalTo(NAME1)));
    }

    @Test
    public void testGetByNameNotFound() throws Exception {
        // given
        when(this.categoryService.getCategoryByName(anyString())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(CategoryController.BASE_URL + "/Foo").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
