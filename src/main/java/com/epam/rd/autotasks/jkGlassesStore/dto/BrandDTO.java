package com.epam.rd.autotasks.jkGlassesStore.dto;

import java.util.List;

public class BrandDTO {
    public Long id;
    public String name;
    private List<ProductDTO> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProducts(List<ProductDTO> collect) {
        this.products = collect;
    }
}
