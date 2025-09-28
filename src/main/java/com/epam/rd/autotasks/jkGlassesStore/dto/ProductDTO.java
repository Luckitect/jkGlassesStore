package com.epam.rd.autotasks.jkGlassesStore.dto;

public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
    private String size;
    private String color;
    private String material;
    private boolean polarized;
    private int stock;
    private boolean sale;
    private String imageUrl;

    private Long brandId;       // keep as Long
    private Long categoryId;    // keep as Long

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    public boolean isPolarized() { return polarized; }
    public void setPolarized(boolean polarized) { this.polarized = polarized; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public boolean isSale() { return sale; }
    public void setSale(boolean sale) { this.sale = sale; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Long getBrandId() { return brandId; }
    public void setBrandId(Long brandId) { this.brandId = brandId; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
}
