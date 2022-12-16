package com.bitsmilez.productmicroservice.core.domain.service.imp;


import com.bitsmilez.productmicroservice.core.domain.model.Categories;
import com.bitsmilez.productmicroservice.core.domain.model.Product;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private BigDecimal price;
    private BigDecimal salesPrice;
    private String img;
    private String description;
    private int stock;
    private Categories category;



    public Product toEntity(){
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(this, Product.class);
    }
}
