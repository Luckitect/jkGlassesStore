package com.epam.rd.autotasks.jkGlassesStore.mapper;

import com.epam.rd.autotasks.jkGlassesStore.dto.ProductDTO;
import com.epam.rd.autotasks.jkGlassesStore.model.Product;

public class ProductMapper {

    public static ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setSize(product.getSize());
        dto.setColor(product.getColor());
        dto.setMaterial(product.getMaterial());
        dto.setPolarized(product.isPolarized());
        dto.setStock(product.getStock());
        dto.setSale(product.isSale());
        dto.setImageUrl(product.getImageUrl());

        if (product.getBrand() != null) {
            dto.setBrandId(product.getBrand().getId());   // assign ID, not name
        }

        if (product.getCategory() != null) {
            dto.setCategoryId(product.getCategory().getId()); // assign ID, not name
        }

        return dto;
    }
}
