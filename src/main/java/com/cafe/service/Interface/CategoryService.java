package com.cafe.service.Interface;
import com.cafe.Dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO);
    CategoryDTO getCategoryById(Long id);
    List<CategoryDTO> getAllCategories();
    void deleteCategory(Long id);
}
