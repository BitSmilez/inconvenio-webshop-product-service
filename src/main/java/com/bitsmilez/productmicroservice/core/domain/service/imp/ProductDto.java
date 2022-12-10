package com.bitsmilez.productmicroservice.core.domain.service.imp;


import com.bitsmilez.productmicroservice.core.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ProductDto {
    private UUID id;
    private String name;
    private Long price;
    private Long salesPrice;
    private String img;
    private String description;
    private int stock;
    private String category;



    public Product toEntity(){
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(this, Product.class);
    }
}
