package com.catalog.services;

import com.catalog.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<ProductDTO> findAllPaged(Pageable pageable);
    ProductDTO findById(Long id);
    ProductDTO insert(ProductDTO productDTO);
    ProductDTO update(Long id, ProductDTO productDTO);
    void delete(Long id);
}
