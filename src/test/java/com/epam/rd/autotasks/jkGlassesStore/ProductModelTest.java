package com.epam.rd.autotasks.jkGlassesStore;


import com.epam.rd.autotasks.jkGlassesStore.model.Product;
import org.junit.jupiter.api.Test;


public class ProductModelTest {

    @Test
    public void testSetName() {
        Product product = new Product();
        product.setName("Test Product");
        assert product.getName().equals("Test Product") : "Cant set name";
    }

    @Test
    public void testSetPrice() {
        Product product = new Product();
        product.setPrice(100.0);
        assert product.getPrice() == 100.0 : "Cant set price";
    }
}
