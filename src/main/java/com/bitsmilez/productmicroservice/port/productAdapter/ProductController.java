package com.bitsmilez.productmicroservice.port.productAdapter;

import com.bitsmilez.productmicroservice.core.domain.service.imp.ProductDto;
import com.bitsmilez.productmicroservice.core.domain.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ProductController {

    @Autowired

    private IProductService productService;

    public ProductController(IProductService productService) {
        super();
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {

        return productService.getAllProducts();
    }

    @GetMapping("product/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable(name = "id") UUID id) {
        ProductDto product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok().body(product);

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product")
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto) {

        productService.createProduct(productDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable UUID id, @RequestBody ProductDto product) {

        if (productService.updateProduct(id, product)) {
            return new ResponseEntity<>(HttpStatus.OK);

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "id") UUID id) {
        if (productService.deleteProduct(id)) {
            return new ResponseEntity<>(HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @GetMapping("product/category/{category}")
    public List<ProductDto> getCategory(@PathVariable(name="category") String category) {


        return productService.getProductsByCategory(String category);
    }

    }
