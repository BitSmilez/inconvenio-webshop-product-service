package com.bitsmilez.productmicroservice.core.domain.model;


import com.bitsmilez.productmicroservice.core.domain.service.imp.ProductDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
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
    private BigDecimal price;
    private BigDecimal salesPrice = null;
    private String img;
    private String description;
    @Column(length = 2048)
    private String detailedDescription;
    private String designer;
    private String productOrigin;
    private String material;
    private int stock;
    private Categories category;

}
