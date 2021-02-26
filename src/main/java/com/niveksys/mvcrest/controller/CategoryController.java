package com.niveksys.mvcrest.controller;

import com.niveksys.mvcrest.dto.CategoryDto;
import com.niveksys.mvcrest.dto.CatorgoryListDto;
import com.niveksys.mvcrest.service.CategoryService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping({ "", "/" })
    public ResponseEntity<CatorgoryListDto> list() {
        log.debug("LIST all the product categories.");
        return new ResponseEntity<CatorgoryListDto>(new CatorgoryListDto(this.categoryService.findAll()),
                HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<CategoryDto> showByName(@PathVariable String name) {
        log.debug("SHOW a category by name.");
        return new ResponseEntity<CategoryDto>(this.categoryService.findByName(name), HttpStatus.OK);
    }
}
