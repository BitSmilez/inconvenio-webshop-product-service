package com.bitsmilez.productmicroservice.core.domain.service.imp;

import com.bitsmilez.productmicroservice.core.domain.model.Categories;
import com.bitsmilez.productmicroservice.core.domain.model.Product;
import com.bitsmilez.productmicroservice.core.domain.service.interfaces.IProductRepository;
import com.bitsmilez.productmicroservice.core.domain.service.interfaces.IProductService;
import com.bitsmilez.productmicroservice.port.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements IProductService {
    private final IProductRepository productRepository;

    public ProductServiceImpl(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> getAllProducts() {

        return productRepository.findAll().stream().map(Mapper::maptoProductDTO).toList();
    }

    @Override
    public ProductDto createProduct(ProductDto product) {

        productRepository.save(Mapper.mapToProduct(product));
        return product;
    }

    @Override
    public boolean updateProduct(UUID id, ProductDto productReq) {

        if (productRepository.existsById(id) && productReq.getId() == id) {

            productRepository.save(Mapper.mapToProduct(productReq));
            return true;
        } else {
            return false;
        }


    }

    @Override
    public boolean deleteProduct(UUID id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public ProductDto getProductById(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(Mapper::maptoProductDTO).orElse(null);

    }

    @Override
    public List<ProductDto> getProductByKeyWord(String keyword) {
        return productRepository.findAllByNameContaining(keyword.toLowerCase()).stream().map(Mapper::maptoProductDTO).toList();
    }

    @Override
    public List<ProductDto> getProductsByCategory(String category) {
        Categories value = null;
        for (Categories categorie : Categories.values()) {
            if (categorie.name().equalsIgnoreCase(category)) {
                value = categorie;
            }
        }
        if (value != null) {
            return productRepository.findAllByCategory(value).stream().map(Mapper::maptoProductDTO).toList();

        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<ProductDto> getProductsOnSale() {
        return productRepository.findAllByOnSale().stream().map(Mapper::maptoProductDTO).toList();
    }

}
