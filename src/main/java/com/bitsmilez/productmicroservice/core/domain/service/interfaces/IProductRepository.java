package com.bitsmilez.productmicroservice.core.domain.service.interfaces;


import com.bitsmilez.productmicroservice.core.domain.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, UUID> {
}
