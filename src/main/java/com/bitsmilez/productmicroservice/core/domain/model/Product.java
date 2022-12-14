package com.bitsmilez.productmicroservice.core.domain.model;


import com.bitsmilez.productmicroservice.core.domain.service.imp.ProductDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private Double price;
    private Double salesPrice = null;
    private String img;
    private String description;
    private int stock;
    private Categories category;


    public ProductDto toDTO(){

        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(this,ProductDto.class);
    }



}
