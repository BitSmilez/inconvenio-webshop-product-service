package com.bitsmilez.productmicroservice.core.domain.service.interfaces;


import com.bitsmilez.productmicroservice.core.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface IProductRepository extends JpaRepository<Product, UUID> {
    @Query(value= "SELECT p FROM Product p WHERE LOWER(p.category) LIKE %?1%")
    List<Product> findAllByCategory(String keyword);
}
