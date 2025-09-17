package com.code81.library.mapper.helper;

import com.code81.library.entity.Category;
import com.code81.library.repository.CategoryRepository;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapperHelper {
    private final CategoryRepository categoryRepository;

    public CategoryMapperHelper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Named("mapCategory")
    public Category mapCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }
}
