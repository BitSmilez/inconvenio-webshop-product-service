package com.bitsmilez.productmicroservice.core.domain.model;


import com.bitsmilez.productmicroservice.core.domain.service.imp.ProductDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private Long price;
    private Long salesPrice = null;
    private String img;
    private String description;
    private int stock;
    private String category;


    public ProductDto toDTO(){

        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(this,ProductDto.class);
    }



}
