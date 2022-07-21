package com.catalog.services.mapper;

import com.catalog.dto.CategoryDTO;
import com.catalog.entities.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<Category, CategoryDTO> {
}
