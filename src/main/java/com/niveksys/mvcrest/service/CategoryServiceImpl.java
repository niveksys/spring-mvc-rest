package com.niveksys.mvcrest.service;

import java.util.List;
import java.util.stream.Collectors;

import com.niveksys.mvcrest.dto.CategoryDto;
import com.niveksys.mvcrest.mapper.CategoryMapper;
import com.niveksys.mvcrest.repository.CategoryRepository;

import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDto> getCategoryList() {
        return this.categoryRepository.findAll().stream().map(this.categoryMapper::categoryToCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryByName(String name) {
        return this.categoryRepository.findByNameIgnoreCase(name).map(this.categoryMapper::categoryToCategoryDto)
                .orElseThrow(RuntimeException::new);
    }

}
