package com.catalog.services.mapper;

import com.catalog.dto.ProductDTO;
import com.catalog.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<Product, ProductDTO> {
}
