package com.bitsmilez.productmicroservice.port.mapper;

import com.bitsmilez.productmicroservice.config.ProductMessage;
import com.bitsmilez.productmicroservice.core.domain.model.Product;
import com.bitsmilez.productmicroservice.core.domain.service.imp.ProductDto;
import org.modelmapper.ModelMapper;

import java.util.UUID;

public class Mapper {

    public static ProductMessage mapToProductMessage(ProductDto productDto, int quantity, String userID) {
        ProductMessage productMessage = new ProductMessage();
        productMessage.setMessageID(UUID.randomUUID().toString());
        productMessage.setProductID(productDto.getId().toString());
        productMessage.setUserID(userID);
        productMessage.setProductName(productDto.getName());
        productMessage.setProductPrice(productDto.getPrice());
        productMessage.setProductSalesPrice(productDto.getSalesPrice());
        productMessage.setProductImg(productDto.getImg());
        productMessage.setQuantity(quantity);
        return productMessage;
    }

    public static Product mapToProduct(ProductDto product) {
        return new ModelMapper().map(product, Product.class);
    }

    public static ProductDto maptoProductDTO(Product product) {
        return new ModelMapper().map(product, ProductDto.class);

    }
}
