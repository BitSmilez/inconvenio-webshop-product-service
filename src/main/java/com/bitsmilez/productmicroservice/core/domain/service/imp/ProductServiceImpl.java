package com.bitsmilez.productmicroservice.core.domain.service.imp;

import com.bitsmilez.productmicroservice.core.domain.model.Product;
import com.bitsmilez.productmicroservice.core.domain.service.interfaces.IProductRepository;
import com.bitsmilez.productmicroservice.core.domain.service.interfaces.IProductService;
import org.springframework.stereotype.Service;

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

        return productRepository.findAll().stream().map(Product::toDTO).toList();
    }

    @Override
    public ProductDto createProduct(ProductDto product) {

        productRepository.save(product.toEntity());
        return product;
    }

    @Override
    public boolean updateProduct(UUID id, ProductDto productReq) {

        if (productRepository.existsById(id) && productReq.getId() == id){

            productRepository.save(productReq.toEntity());
            return true;
        }
        else{
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
        if(product.isPresent()){
            return product.get().toDTO();
        }

        return null;

    }

    @Override
    public List<ProductDto> getProductByKeyWord(String keyword) {
       return productRepository.findAllByNameContaining(keyword.toLowerCase()).stream().map(Product::toDTO).toList();
    }
}
