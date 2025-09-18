package com.code81.library.service.impl;

import com.code81.library.dto.CategoryDTO;
import com.code81.library.entity.Category;
import com.code81.library.exception.ResourceNotFoundException;
import com.code81.library.mapper.CategoryMapper;
import com.code81.library.repository.CategoryRepository;
import com.code81.library.service.CategoryService;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.toEntity(categoryDTO);
        return categoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        Category updated = categoryMapper.toEntity(categoryDTO);
        updated.setId(existing.getId());

        return categoryMapper.toDTO(categoryRepository.save(updated));
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        return categoryMapper.toDTO(
                categoryRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id))
        );
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new ResourceNotFoundException("No categories available");
        }
        return categoryMapper.toDTOs(categories);
    }
}
