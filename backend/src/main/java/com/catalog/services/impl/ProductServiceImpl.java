package com.catalog.services.impl;

import com.catalog.dto.ProductDTO;
import com.catalog.entities.Product;
import com.catalog.repositories.ProductRepository;
import com.catalog.services.ProductService;
import com.catalog.services.exceptions.DatabaseException;
import com.catalog.services.exceptions.ResourceNotFoundException;
import com.catalog.services.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(Pageable pageable) {
        return productRepository.findAll(pageable).map(productMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return productMapper.toDTO(product);
    }

    @Transactional
    public ProductDTO insert(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        product = productRepository.save(product);
        return productMapper.toDTO(product);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        product.setId(productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found")).getId());
        product = productRepository.save(product);
        return productMapper.toDTO(product);
    }

    @Transactional
    public void delete(Long id) {
        try {
            productRepository.deleteById(id);
            productRepository.flush();
        } catch (EmptyResultDataAccessException exception) {
            throw new ResourceNotFoundException("Entity not found");
        } catch (DataIntegrityViolationException exception) {
            throw new DatabaseException("Integrity violation");
        }
    }
}
