package com.bitsmilez.productmicroservice.core.domain.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;
    private String name;
    private Long price;
    private Long salesPrice = null;
    private String img;
    private String description;
    private int stock;
    private String category;





}
