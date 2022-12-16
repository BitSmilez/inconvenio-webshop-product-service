package com.bitsmilez.productmicroservice.core.domain.service.interfaces;

import com.bitsmilez.productmicroservice.core.domain.service.imp.ProductDto;

import java.util.List;
import java.util.UUID;

public interface IProductService {
    
    List<ProductDto> getAllProducts();

    ProductDto createProduct (ProductDto product);


    boolean updateProduct(UUID id, ProductDto productReq);


    boolean deleteProduct(UUID id);


    ProductDto getProductById(UUID id);
}

