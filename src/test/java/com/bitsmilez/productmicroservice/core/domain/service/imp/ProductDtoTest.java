package com.bitsmilez.productmicroservice.core.domain.service.imp;

import com.bitsmilez.productmicroservice.core.domain.model.Product;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductDtoTest {

    @Test
    void sameAttributes() {

        Field[] allFieldsProducts = Product.class.getDeclaredFields();
        List<String> exp = Arrays.stream(allFieldsProducts).map(field -> field.toString().split("\\.")).toList().stream().map(p -> p[p.length - 1]).toList();

        Field[] allFieldsProductsDTO = ProductDto.class.getDeclaredFields();
        List<String> actual = Arrays.stream(allFieldsProductsDTO).map(field -> field.toString().split("\\.")).toList().stream().map(p -> p[p.length - 1]).toList();
        assertEquals(exp, actual);

    }


}