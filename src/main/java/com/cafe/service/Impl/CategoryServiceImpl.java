package com.cafe.service.Impl;
import com.cafe.Dto.CategoryDTO;
import com.cafe.entity.Category;
import com.cafe.repository.CategoryRepository;
import com.cafe.service.Interface.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        return modelMapper.map(categoryRepository.save(category), CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(categoryDTO.getName());
        return modelMapper.map(categoryRepository.save(category), CategoryDTO.class);
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        return modelMapper.map(
                categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found")),
                CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(cat -> modelMapper.map(cat, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
