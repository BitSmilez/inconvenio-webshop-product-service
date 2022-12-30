package com.bitsmilez.productmicroservice.port.MQAdapter;

import com.bitsmilez.productmicroservice.config.ProductMessage;
import com.bitsmilez.productmicroservice.core.domain.service.imp.ProductDto;
import com.bitsmilez.productmicroservice.port.mapper.ProductMessageMapper;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductMessageMapperTest {

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
        ProductMessage productMessage = ProductMessageMapper.mapToProductMessage(productDto, quantity);
        // Assert
        assertEquals(productDto.getId().toString(), productMessage.getProductID());
        assertEquals(productDto.getName(), productMessage.getProductName());
        assertEquals(productDto.getPrice(), productMessage.getProductPrice());
        assertEquals(productDto.getSalesPrice(), productMessage.getProductSalesPrice());
        assertEquals(productDto.getImg(), productMessage.getProductImg());
        assertEquals(quantity, productMessage.getQuantity());
    }
}