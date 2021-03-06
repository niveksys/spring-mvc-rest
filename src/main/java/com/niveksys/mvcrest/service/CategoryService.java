package com.niveksys.mvcrest.service;

import java.util.List;

import com.niveksys.mvcrest.dto.CategoryDto;

public interface CategoryService {

    List<CategoryDto> findAll();

    CategoryDto findByName(String name);

}
