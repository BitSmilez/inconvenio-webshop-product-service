package com.bitsmilez.productmicroservice.core.domain.service.imp;

import com.bitsmilez.productmicroservice.core.domain.model.Categories;
import com.bitsmilez.productmicroservice.core.domain.model.Product;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductDtoTest {

    @Test
    void sameAttributes() {


        Field[] allFieldsProducts = Product.class.getDeclaredFields();
        List<String> exp = Arrays.stream(allFieldsProducts).map(field -> field.toString().split("\\.")).toList().stream().map(p -> p[p.length - 1]).toList();

        Field[] allFieldsProductsDTO = ProductDto.class.getDeclaredFields();
        List<String> actual = Arrays.stream(allFieldsProductsDTO).map(field -> field.toString().split("\\.")).toList().stream().map(p -> p[p.length - 1].toString()).toList();
        assertEquals(exp, actual);


    }

    @Test
    void convertToEntity() {
        UUID id = new UUID(5, 5);
        String name = "Test";
        BigDecimal price = new BigDecimal("50.00");
        BigDecimal salesPrice = null;
        String img = "img";
        String description = "Description for product";
        int stock = 20;
        Categories category = Categories.Cloths;
        Product product = new Product(id, name, price, salesPrice, img, description, stock, category);
        Product productDTO = new ProductDto(id, name, price, salesPrice, img, description, stock, category).toEntity();

        assertEquals(productDTO, product);

    }

    @Test
    void convertToDTO() {
        UUID id = new UUID(5, 5);
        String name = "Test";
        BigDecimal price = new BigDecimal("50.00");
        BigDecimal salesPrice = null;
        String img = "img";
        String description = "Description for product";
        int stock = 20;
        Categories category = Categories.Cloths;
        ProductDto product = new Product(id, name, price, salesPrice, img, description, stock, category).toDTO();
        ProductDto productDTO = new ProductDto(id, name, price, salesPrice, img, description, stock, category);

        assertEquals(product, productDTO);
    }


}