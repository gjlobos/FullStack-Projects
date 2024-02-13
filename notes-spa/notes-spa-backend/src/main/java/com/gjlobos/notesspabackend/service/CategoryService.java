package com.gjlobos.notesspabackend.service;

import com.gjlobos.notesspabackend.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();
    CategoryDTO createCategory (CategoryDTO categoryDTO);
    void removeCategory(Long categoryId);
}
