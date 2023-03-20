package com.bitsmilez.productmicroservice.port.MQAdapter;

import com.bitsmilez.productmicroservice.config.MQConfig;
import com.bitsmilez.productmicroservice.config.ProductMessage;
import com.bitsmilez.productmicroservice.core.domain.service.imp.ProductDto;
import com.bitsmilez.productmicroservice.core.domain.service.imp.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ProductProducerTest {

    @Mock
    private RabbitTemplate productTemplate;
    @Mock
    private ProductServiceImpl productService;

    @InjectMocks
    private ProductProducer productProducer;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(productProducer).build();
    }


    @Test
    void publishAddToCartEvent() throws Exception {
        ObjectNode objectNode = objectMapper.createObjectNode();
        UUID id = UUID.randomUUID();
        int qty = 1;
        String cartID = "123";
        objectNode.put("productID", id.toString());
        objectNode.put("quantity", qty);
        objectNode.put("cartID", cartID);
        ProductDto productDto = new ProductDto();
        productDto.setId(id);
        productDto.setStock(qty);

        when(productService.getProductById(id)).thenReturn(productDto);

        mockMvc.perform(post("/cart/add-to-cart")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(objectNode)))
                .andExpect(status().isOk());

        verify(productService, times(1)).getProductById(id);
        verify(productTemplate, times(1)).convertAndSend(
                eq(MQConfig.PRODUCT_EXCHANGE),
                eq(MQConfig.PRODUCT_TOPIC_ADD_TO_CART),
                any(ProductMessage.class)
        );

    }

    @Test
    void publishAddToCartEventWithUnkownProduct() throws Exception {
        ObjectNode objectNode = objectMapper.createObjectNode();
        UUID id = UUID.randomUUID();
        int qty = 1;
        String cartID = "123";
        objectNode.put("productID", id.toString());
        objectNode.put("quantity", qty);
        objectNode.put("cartID", cartID);


        when(productService.getProductById(id)).thenReturn(null);

        mockMvc.perform(post("/cart/add-to-cart")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(objectNode)))
                .andExpect(status().isNotFound());

        verify(productService, times(1)).getProductById(id);
        verify(productTemplate, times(0)).convertAndSend(
                eq(MQConfig.PRODUCT_EXCHANGE),
                eq(MQConfig.PRODUCT_TOPIC_ADD_TO_CART),
                any(ProductMessage.class)
        );

    }

    @Test
    void publishRemoveFromCartEvent() throws Exception {
        ObjectNode objectNode = objectMapper.createObjectNode();
        UUID id = UUID.randomUUID();
        int qty = 1;
        String cartID = "123";

        objectNode.put("productID", id.toString());
        objectNode.put("quantity", qty);
        objectNode.put("cartID", cartID);
        ProductDto productDto = new ProductDto();
        productDto.setId(id);
        productDto.setStock(qty);

        when(productService.getProductById(id)).thenReturn(productDto);
        mockMvc.perform(post("/cart/remove-from-cart")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(objectNode)))
                .andExpect(status().isOk());

        verify(productService, times(1)).getProductById(id);
        verify(productTemplate, times(1)).convertAndSend(
                eq(MQConfig.PRODUCT_EXCHANGE),
                eq(MQConfig.PRODUCT_TOPIC_REMOVE_FROM_CART),
                any(ProductMessage.class)
        );



    }
    @Test
    void publishRemoveFromCartEventWithUnkownProduct() throws Exception {
        ObjectNode objectNode = objectMapper.createObjectNode();
        UUID id = UUID.randomUUID();
        int qty = 1;
        String cartID = "123";
        objectNode.put("productID", String.valueOf(id));
        objectNode.put("quantity", qty);
        objectNode.put("cartID", cartID);

        when(productService.getProductById(id)).thenReturn(null);

        mockMvc.perform(post("/cart/remove-from-cart")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(objectNode)))
                .andExpect(status().isNotFound());

        verify(productService, times(1)).getProductById(id);
        verify(productTemplate, times(0)).convertAndSend(
                eq(MQConfig.PRODUCT_EXCHANGE),
                eq(MQConfig.PRODUCT_TOPIC_REMOVE_FROM_CART),
                any(ProductMessage.class));

    }
    @Test
    void publishUpdateCartEvent() throws Exception {
        ObjectNode objectNode = objectMapper.createObjectNode();
        UUID id = UUID.randomUUID();
        int qty = 1;
        String cartID = "123";
        objectNode.put("productID", id.toString());
        objectNode.put("quantity", qty);
        objectNode.put("cartID", cartID);
        ProductDto productDto = new ProductDto();
        productDto.setId(id);
        productDto.setStock(qty);

        when(productService.getProductById(id)).thenReturn(productDto);

        mockMvc.perform(post("/cart/update-cart")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(objectNode)))
                .andExpect(status().isOk());
        verify(productTemplate, times(1)).convertAndSend(
                eq(MQConfig.PRODUCT_EXCHANGE),
                eq(MQConfig.PRODUCT_TOPIC_UPDATE_CART),
                any(ProductMessage.class)
        );
    }

    @Test
    void publishUpdateCartEventWithUnkownProduct() throws Exception {
        ObjectNode objectNode = objectMapper.createObjectNode();
        UUID id = UUID.randomUUID();
        int qty = 1;
        String cartID = "123";
        objectNode.put("productID", id.toString());
        objectNode.put("quantity", qty);
        objectNode.put("cartID", cartID);

        when(productService.getProductById(id)).thenReturn(null);

        mockMvc.perform(post("/cart/update-cart")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(objectNode)))
                .andExpect(status().isNotFound());

        verify(productService, times(1)).getProductById(id);
        verify(productTemplate, times(0)).convertAndSend(
                eq(MQConfig.PRODUCT_EXCHANGE),
                eq(MQConfig.PRODUCT_TOPIC_UPDATE_CART),
                any(ProductMessage.class)
        );
    }
}