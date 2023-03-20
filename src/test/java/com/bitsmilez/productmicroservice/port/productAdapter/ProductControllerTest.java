package com.bitsmilez.productmicroservice.port.productAdapter;

import com.bitsmilez.productmicroservice.core.domain.model.Categories;
import com.bitsmilez.productmicroservice.core.domain.model.Product;
import com.bitsmilez.productmicroservice.core.domain.service.imp.ProductDto;
import com.bitsmilez.productmicroservice.core.domain.service.imp.ProductServiceImpl;
import com.bitsmilez.productmicroservice.port.mapper.Mapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/application.properties" })
@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    ProductServiceImpl productService;

    @InjectMocks
    ProductController productController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;


    final List<ProductDto> productDtos = new LinkedList<>();
    final List<Product> products = new LinkedList<>();
    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
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

    @Test
    void getAllProducts() throws Exception {

        when(productService.getAllProducts()).thenReturn(productDtos);
        MvcResult res = mockMvc.perform(get("/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = res.getResponse().getContentAsString();
        List<ProductDto> res_productDTOS = objectMapper.readValue(content, objectMapper.getTypeFactory().constructCollectionType(List.class, ProductDto.class));
        assertEquals(productDtos,res_productDTOS);
    }

    @Test
    void getProductById() throws Exception {
        int randdInt = new Random().nextInt(products.size());
        Product product = products.get(randdInt);
        ProductDto productDto = productDtos.get(randdInt);
        when(productService.getProductById(product.getId())).thenReturn(productDto);

        MvcResult res = mockMvc.perform(get("/product/" + product.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String content = res.getResponse().getContentAsString();
        ProductDto res_productDTOS = objectMapper.readValue(content, ProductDto.class);
        assertEquals(productDto,res_productDTOS);


    }

    @Test
    void getProductsByIdUnkown() throws Exception {
        UUID id = UUID.randomUUID();
        when(productService.getProductById(id)).thenReturn(null);

        mockMvc.perform(get("/product/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

    }
    @Test
    void createProduct() throws Exception {
        Product product = products.get(0);
        ProductDto dto = Mapper.maptoProductDTO(product);
        when(productService.createProduct(dto)).thenReturn(dto);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/product").content(objectMapper.writeValueAsString(dto)).
                        contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

        verify(productService).createProduct(dto);


    }

    @Test
    void updateProduct() throws Exception {
        int randdInt = new Random().nextInt(products.size());
        Product product = products.get(randdInt);
        product.setName("Updated Product");
        ProductDto dto = Mapper.maptoProductDTO(product);
        when(productService.updateProduct(product.getId(),dto)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/product/"+product.getId()).content(objectMapper.writeValueAsString(dto)).
                contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());


    }
    @Test
    void updateUnknownProduct() throws Exception {
        UUID id = UUID.randomUUID();
        Product product = products.get(0);
        product.setId(id);
        product.setName("Updated Product");
        ProductDto dto = Mapper.maptoProductDTO(product);


        when(productService.updateProduct(id,dto)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/product/"+id).content(objectMapper.writeValueAsString(dto)).
                contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

    }
    @Test
    void deleteProduct() throws Exception {
        int randdInt = new Random().nextInt(products.size());
        Product product = products.get(randdInt);
        when(productService.deleteProduct(product.getId())).thenReturn(true);

        mockMvc.perform(delete("/product/" + product.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    @Test
    void deleteUnknownProduct() throws Exception {
        UUID id = UUID.randomUUID();
        when(productService.deleteProduct(id)).thenReturn(false);

        mockMvc.perform(delete("/product/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }



    @Test
    void findProducts() throws Exception {
        int randInt = new Random().nextInt(products.size());
        Product product = products.get(randInt);
        ProductDto productDto = productDtos.get(randInt);
        LinkedList<ProductDto> exp = new LinkedList<>();
        exp.add(productDto);
        when(productService.getProductByKeyWord(product.getName())).thenReturn(exp);
        MvcResult res = mockMvc.perform(get("/product/find").param("keyWord", product.getName())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String content = res.getResponse().getContentAsString();
        List<ProductDto> res_productDTOS = objectMapper.readValue(content, objectMapper.getTypeFactory().constructCollectionType(List.class, ProductDto.class));
        assertEquals(exp,res_productDTOS);


    }

    @Test
    void getCategory() throws Exception {
        Categories[] category = Categories.values();
        int randInt = new Random().nextInt(category.length);
        Categories category1 = category[randInt];
        LinkedList<Product> tmp = new LinkedList<>();
        for (Product product : products) {
            if (product.getCategory() == category1) {
                tmp.add(product);
            }
        }
        LinkedList<ProductDto> expected = new LinkedList<>();
        for (Product product : tmp) {
            expected.add(Mapper.maptoProductDTO(product));
        }

        when(productService.getProductsByCategory(category1.toString())).thenReturn(expected);
        MvcResult res = mockMvc.perform(get("/product/category/"
                + category1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String content = res.getResponse().getContentAsString();
        List<ProductDto> res_productDTOS = objectMapper.readValue(content, objectMapper.getTypeFactory().constructCollectionType(List.class, ProductDto.class));
        assertEquals(expected,res_productDTOS);

    }

    @Test
    void getProductsOnSale() throws Exception {
        LinkedList<Product> tmp = new LinkedList<>();
        for (Product product : products) {
            if (product.getSalesPrice() != null) {
                tmp.add(product);
            }
        }

        LinkedList<ProductDto> expected = new LinkedList<>();
        for (Product product : tmp) {
            expected.add(Mapper.maptoProductDTO(product));
        }

        when(productService.getProductsOnSale()).thenReturn(expected);
        MvcResult res = mockMvc.perform(get("/products/sale")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = res.getResponse().getContentAsString();
        List<ProductDto> res_productDTOS = objectMapper.readValue(content, objectMapper.getTypeFactory().constructCollectionType(List.class, ProductDto.class));
        assertEquals(expected,res_productDTOS);
    }

    @Test
    void getProductsOnSaleUnknown() throws Exception {
        when(productService.getProductsOnSale()).thenReturn(new LinkedList<>());
        MvcResult res = mockMvc.perform(get("/products/sale")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String content = res.getResponse().getContentAsString();
        List<ProductDto> res_productDTOS = objectMapper.readValue(content, objectMapper.getTypeFactory().constructCollectionType(List.class, ProductDto.class));
        assertEquals(new LinkedList<>(),res_productDTOS);



    }
}