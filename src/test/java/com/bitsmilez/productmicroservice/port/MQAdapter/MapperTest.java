package com.bitsmilez.productmicroservice.port.MQAdapter;

import com.bitsmilez.productmicroservice.config.ProductMessage;
import com.bitsmilez.productmicroservice.core.domain.model.Categories;
import com.bitsmilez.productmicroservice.core.domain.model.Product;
import com.bitsmilez.productmicroservice.core.domain.service.imp.ProductDto;
import com.bitsmilez.productmicroservice.port.mapper.Mapper;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MapperTest {

    @Test
    void mapToProductMessageTest() {
        // Setup ProductDto
        ProductDto productDto = new ProductDto();
        productDto.setId(UUID.randomUUID());
        productDto.setName("test");
        productDto.setPrice(BigDecimal.valueOf(10));
        productDto.setSalesPrice(BigDecimal.valueOf(5));
        productDto.setImg("test");
        int quantity = 1;
        // Map to ProductMessage
        ProductMessage productMessage = Mapper.mapToProductMessage(productDto, quantity);
        // Assert
        assertEquals(productDto.getId().toString(), productMessage.getProductID());
        assertEquals(productDto.getName(), productMessage.getProductName());
        assertEquals(productDto.getPrice(), productMessage.getProductPrice());
        assertEquals(productDto.getSalesPrice(), productMessage.getProductSalesPrice());
        assertEquals(productDto.getImg(), productMessage.getProductImg());
        assertEquals(quantity, productMessage.getQuantity());
    }

    @Test
    void convertToEntity() {
        UUID id = new UUID(5, 5);
        String name = "Test";
        BigDecimal price = new BigDecimal("50.00");
        BigDecimal salesPrice = new BigDecimal("25.00");
        String img = "img";
        String description = "Description for product";
        String detailedDescription = "Detailed description for product";
        String designer = "Designer";
        String productOrigin = "Product origin";
        String material = "Material";
        int stock = 20;
        Categories category = Categories.Clothes;
        Product product = new Product(id, name, price, salesPrice, img, description, detailedDescription, designer, productOrigin, material, stock, category);
        Product mappedProduct = Mapper.mapToProduct(new ProductDto(id, name, price, salesPrice, img, description, detailedDescription, designer, productOrigin, material, stock, category));
        assertEquals(mappedProduct, product);

    }

    @Test
    void convertToDTO() {
        UUID id = new UUID(5, 5);
        String name = "Test";
        BigDecimal price = new BigDecimal("50.00");
        BigDecimal salesPrice = new BigDecimal("25.00");
        String img = "img";
        String description = "Description for product";
        String detailedDescription = "Detailed description for product";
        String designer = "Designer";
        String productOrigin = "Product origin";
        String material = "Material";
        int stock = 20;
        Categories category = Categories.Clothes;
        ProductDto productDTO = new ProductDto(id, name, price, salesPrice, img, description, detailedDescription, designer, productOrigin, material, stock, category);
        ProductDto mappedProductDTO = Mapper.maptoProductDTO(new Product(id, name, price, salesPrice, img, description, detailedDescription, designer, productOrigin, material, stock, category));

        assertEquals(mappedProductDTO, productDTO);
    }

}