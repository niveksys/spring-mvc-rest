package com.niveksys.mvcrest.repository;

import com.niveksys.mvcrest.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
