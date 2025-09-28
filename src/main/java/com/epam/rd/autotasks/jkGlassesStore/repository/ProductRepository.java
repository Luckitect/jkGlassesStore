package com.epam.rd.autotasks.jkGlassesStore.repository;

import com.epam.rd.autotasks.jkGlassesStore.model.Product;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


@Repository
public interface ProductRepository extends  JpaRepository<Product, Long>{

    List<Product> findByCategory_Name(String category);
    List<Product> findByBrand_Id(long id);
    List<Product> findByPriceBetween(double min, double max);
    List<Product> findBySize(String size);
    List<Product> findByColor(String color);
    List<Product> findByMaterial(String material);
    List<Product> findByPolarizedTrue();
    List<Product> findBySaleTrue();
    List<Product> findByStockGreaterThan(int stock);


    List<Product> findByBrandId(Long brandId);

    List<Product> findByCategoryName(String categoryName);
}
