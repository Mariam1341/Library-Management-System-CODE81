package com.code81.library.mapper;

import com.code81.library.dto.CategoryDTO;
import com.code81.library.entity.Category;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toDTO(Category category);

    Category toEntity(CategoryDTO categoryDTO);

    List<CategoryDTO> toDTOs(List<Category> categories);

    List<Category> toEntities(List<CategoryDTO> categoryDTOs);
}
