package com.niveksys.mvcrest.controller;

import com.niveksys.mvcrest.dto.CategoryDto;
import com.niveksys.mvcrest.dto.CatorgoryListDto;
import com.niveksys.mvcrest.service.CategoryService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/api/v1/categories";

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping({ "", "/" })
    @ResponseStatus(HttpStatus.OK)
    public CatorgoryListDto getCategoryList() {
        log.debug("LIST all the product categories.");
        return new CatorgoryListDto(this.categoryService.getCategoryList());
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto getCategoryByName(@PathVariable String name) {
        log.debug("SHOW a category by name.");
        return this.categoryService.getCategoryByName(name);
    }
}
