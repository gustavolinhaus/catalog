package com.catalog.services;

import com.catalog.dto.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface CategoryService {
    Page<CategoryDTO> findAllPaged(PageRequest pageRequest);
    CategoryDTO findById(Long id);
    CategoryDTO insert(CategoryDTO categoryDTO);
    CategoryDTO update(Long id, CategoryDTO categoryDTO);
    void delete(Long id);
}
