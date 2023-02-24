package com.bitsmilez.productmicroservice.core.domain.service.imp;

import com.bitsmilez.productmicroservice.core.domain.model.Categories;
import com.bitsmilez.productmicroservice.core.domain.model.Product;
import com.bitsmilez.productmicroservice.core.domain.service.interfaces.IProductRepository;
import com.bitsmilez.productmicroservice.port.mapper.Mapper;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import redis.embedded.RedisServer;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    private IProductRepository repository;


    ProductServiceImpl service;

    final List<ProductDto> productDtos = new LinkedList<>();
    final List<Product> products = new LinkedList<>();


    private RedisServer redisServer;
    @BeforeEach
    public void setUp() {
        service = new ProductServiceImpl(repository);
        Categories[] category = Categories.values();


        for (int i = 0; i < 15; i++) {
            int rnd = new Random().nextInt(category.length);
            Product product = new Product();
            product.setId(UUID.randomUUID());
            product.setName("Product " + i);
            product.setPrice(new BigDecimal(100 + i));
            product.setCategory(category[rnd]);
            product.setName("Product " + i);
            if (i%2 == 0) {
                product.setSalesPrice(new BigDecimal(100 + i));


            }
            productDtos.add(Mapper.maptoProductDTO(product));
            products.add(product);
        }

    }
    @Before
    public void setUpRedis() {
        try {
            redisServer = new RedisServer(6379);
            redisServer.start();
        } catch (Exception e) {
            //do nothing
        }
    }

    @After
    public void tearDown() {
        try {
            redisServer.stop();
        } catch (Exception e) {
            //do nothing
        }
    }
    @Test
    void getAllProducts() {

        when (repository.findAll()).thenReturn(products);
        List<ProductDto> res = service.getAllProducts();

        assertEquals(productDtos, res);




    }

    @Test
    void getProductById() {

        int rnd = new Random().nextInt(products.size());
        Product product = products.get(rnd);
        ProductDto productDto = productDtos.get(rnd);
        when (repository.findById(product.getId())).thenReturn(java.util.Optional.of(product));
        ProductDto res = service.getProductById(product.getId());

        assertEquals((productDto), res);


    }
    @Test
    void getUnknownProductById() {
        when(repository.findById(any(UUID.class))).thenReturn(java.util.Optional.empty());
        ProductDto res = service.getProductById(UUID.randomUUID());
        assertNull(res);

    }

    @Test
    void createProduct() {
        Product newProduct = new Product();
        newProduct.setId(UUID.randomUUID());
        newProduct.setName("Product " + 15);
        newProduct.setPrice(new BigDecimal(100 + 15));
        newProduct.setName("Product " + 15);
        newProduct.setSalesPrice(new BigDecimal(100 + 15));

        ProductDto newProductDto = Mapper.maptoProductDTO(newProduct);
        when (repository.save(newProduct)).thenReturn(newProduct);
        ProductDto res = service.createProduct(newProductDto);
        assertEquals(newProductDto, res);


    }

    @Test
    void updateProduct() {
        int rnd = new Random().nextInt(products.size());
        Product product = products.get(rnd);
        product.setName("Product " + "updated");
        UUID id = product.getId();
        when (repository.existsById(id)).thenReturn(true);
        when (repository.save(product)).thenReturn(product);
        boolean res = service.updateProduct(id, Mapper.maptoProductDTO(product));
        assertTrue( res);

    }
    @Test
    void updateUnknownProduct() {
        when(repository.existsById(any(UUID.class))).thenReturn(false);
        boolean res = service.updateProduct(UUID.randomUUID(), new ProductDto());
        assertFalse( res);

    }
    @Test
    void deleteProduct() {
        int rnd = new Random().nextInt(products.size());
        Product product = products.get(rnd);
        UUID id = product.getId();
        when (repository.existsById(id)).thenReturn(true);
        boolean res = service.deleteProduct(id);
        assertTrue( res);

        //check if product is deleted
        products.remove(rnd);
        productDtos.remove(rnd);
        when (repository.findAll()).thenReturn(products);
        List<ProductDto> res2 = service.getAllProducts();
        assertEquals(productDtos, res2);



    }

    @Test
    void deleteUnknownProduct() {
        when(repository.existsById(any(UUID.class))).thenReturn(false);
        boolean res = service.deleteProduct(UUID.randomUUID());
        assertFalse( res);

    }

    @Test
    void findUnknownProducts() {
        int randInt = new Random().nextInt(products.size());
        Product product = products.get(randInt);
        product.setName("Product " + "unknown");
        when (repository.findAllByNameContaining(any(String.class))).thenReturn(new LinkedList<>() {
        });
        List<ProductDto> res = service.getProductByKeyWord(product.getName());
        List<ProductDto> expected = new LinkedList<>();
        assertEquals(expected, res);



    }
    @Test
    void findProducts() {
        int randInt = new Random().nextInt(products.size());
        Product product = products.get(randInt);
        when(repository.findAllByNameContaining(matches(".*" + "product" + "*"))).thenReturn(products);
        List<ProductDto> res = service.getProductByKeyWord(product.getName());
        assertEquals(productDtos, res);
    }

    @Test
    void find2Products(){
        int randInt = new Random().nextInt(products.size());
        Product product = products.get(randInt);
        int randInt2 = new Random().nextInt(products.size());
        if (randInt2 == randInt) {
            randInt2 = randInt + 1;
        }
        Product product2 = products.get(randInt2);
        product.setName(product.getName() + " " + product2.getName());

        LinkedList <Product> products1 = new LinkedList<>();
        products1.add(product);
        products1.add(product2);
        when(repository.findAllByNameContaining(any(String.class))).thenReturn(products1);

        List<ProductDto> res = service.getProductByKeyWord(product2.getName());
        List<ProductDto> expected = new LinkedList<>();
        expected.add(Mapper.maptoProductDTO(product));
        expected.add(Mapper.maptoProductDTO(product2));

        assertEquals(expected, res);

    }

    @Test
    void getCategory() {
        Categories[] category = Categories.values();
        int randInt = new Random().nextInt(category.length);
        Categories category1 = category[randInt];
        LinkedList<Product> expected = new LinkedList<>();
        for (Product product : products) {
            if (product.getCategory() == category1) {
                expected.add(product);
            }
        }
        when(repository.findAllByCategory(category1)).thenReturn(expected);
        List<ProductDto> res = service.getProductsByCategory(category1.name());
        List<ProductDto> expected2 = new LinkedList<>();
        for (Product product : expected) {
            expected2.add(Mapper.maptoProductDTO(product));
        }
        assertEquals(expected2, res);

    }
    @Test
    void getCategoryUnknown() {

        List<ProductDto> res = service.getProductsByCategory("unknown");
        List<ProductDto> expected2 = new LinkedList<>();
        assertEquals(expected2, res);

    }



    @Test
    void getProductsOnSale() {
        LinkedList<Product> expected = new LinkedList<>();
        for (Product product : products) {
            if (product.getSalesPrice() != null) {
                expected.add(product);
            }
        }
        when(repository.findAllByOnSale()).thenReturn(expected);
        List<ProductDto> res = service.getProductsOnSale();
        List<ProductDto> expected2 = new LinkedList<>();
        for (Product product : expected) {
            expected2.add(Mapper.maptoProductDTO(product));
        }
        assertEquals(expected2, res);

    }
    @Test
    void getProductsOnSaleUnknown() {
        when(repository.findAllByOnSale()).thenReturn(new LinkedList<>());
        List<ProductDto> res = service.getProductsOnSale();
        List<ProductDto> expected2 = new LinkedList<>();
        assertEquals(expected2, res);

    }
}