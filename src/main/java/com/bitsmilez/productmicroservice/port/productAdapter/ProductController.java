package com.bitsmilez.productmicroservice.port.productAdapter;

import com.bitsmilez.productmicroservice.core.domain.service.imp.ProductDto;
import com.bitsmilez.productmicroservice.core.domain.service.interfaces.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired

    private IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> getAllPosts() {

        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getPostById(@PathVariable(name = "id") UUID id) {
        ProductDto product = productService.getProductById(id);
        if(!product.equals(null)){
            return ResponseEntity.ok().body(product);

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody ProductDto productDto) {


        productService.createProduct(productDto);


        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable UUID id, @RequestBody ProductDto product) {


        if( productService.updateProduct(id, product)){
            return new ResponseEntity<>(HttpStatus.OK);

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable(name = "id") UUID id) {
        if(productService.deleteProduct(id)){
            return new ResponseEntity<>(HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }








}
