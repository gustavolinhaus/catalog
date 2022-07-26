package com.catalog.services;

import com.catalog.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ProductService {
    Page<ProductDTO> findAllPaged(PageRequest pageRequest);
    ProductDTO findById(Long id);
    ProductDTO insert(ProductDTO productDTO);
    ProductDTO update(Long id, ProductDTO productDTO);
    void delete(Long id);
}
