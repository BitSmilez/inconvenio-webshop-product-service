package com.bitsmilez.productmicroservice.port.mapper;

import com.bitsmilez.productmicroservice.config.ProductMessage;
import com.bitsmilez.productmicroservice.core.domain.service.imp.ProductDto;

import java.util.UUID;

public class ProductMessageMapper {

    public static ProductMessage mapToProductMessage(ProductDto productDto, int quantity) {
        ProductMessage productMessage = new ProductMessage();
        productMessage.setMessageID(UUID.randomUUID().toString());
        productMessage.setProductID(productDto.getId().toString());
        productMessage.setUserID("1");
        productMessage.setProductName(productDto.getName());
        productMessage.setProductPrice(productDto.getPrice());
        productMessage.setProductSalesPrice(productDto.getSalesPrice());
        productMessage.setProductImg(productDto.getImg());
        productMessage.setQuantity(quantity);
        return productMessage;
    }
}
