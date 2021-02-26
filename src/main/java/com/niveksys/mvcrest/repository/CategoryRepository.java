package com.niveksys.mvcrest.repository;

import java.util.Optional;

import com.niveksys.mvcrest.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByNameIgnoreCase(String name);
}
