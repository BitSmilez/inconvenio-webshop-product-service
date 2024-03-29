package com.bitsmilez.productmicroservice.core.domain.service.imp;

import com.bitsmilez.productmicroservice.core.domain.model.Categories;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private String detailedDescription;
    private String designer;
    private String productOrigin;
    private String material;
    private int stock;
    private Categories category;

}
