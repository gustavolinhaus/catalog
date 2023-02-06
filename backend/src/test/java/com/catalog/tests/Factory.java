package com.catalog.tests;

import com.catalog.dto.CategoryDTO;
import com.catalog.dto.ProductDTO;
import com.catalog.entities.Category;
import com.catalog.entities.Product;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Factory {

    public static Product createProduct() {
        Set<Category> categories = new HashSet<>();
        categories.add(new Category(2L, "Electronics"));
        return new Product(1L, "Phone", "Good Phone", 800.0, "https:://img.com/img.png", Instant.parse("2023-02-04T03:00:00Z"), categories);
    }

    public static ProductDTO createProductDTO() {
        Product product = createProduct();
        List<CategoryDTO> categoriesDTO = new ArrayList<>();
        product.getCategories().forEach(category -> {
            CategoryDTO categoryDTO = new CategoryDTO(category.getId(), category.getName());
            categoriesDTO.add(categoryDTO);

        });
        return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getImgUrl(), product.getDate(), categoriesDTO);
    }
}
