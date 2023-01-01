package com.bitsmilez.productmicroservice.port.MQAdapter;

import com.bitsmilez.productmicroservice.config.MQConfig;
import com.bitsmilez.productmicroservice.config.ProductMessage;
import com.bitsmilez.productmicroservice.core.domain.service.imp.ProductDto;
import com.bitsmilez.productmicroservice.core.domain.service.interfaces.IProductService;
import com.bitsmilez.productmicroservice.port.mapper.Mapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductProducer {

    @Autowired
    private RabbitTemplate productTemplate;
    @Autowired
    private IProductService productService;

    public ProductProducer(IProductService productService) {
        super();
        this.productService = productService;
    }

    @PostMapping("/add-to-cart")
    public ResponseEntity<?> publishAddToCartEvent(@RequestBody ObjectNode objectNode) {
        String productID = objectNode.get("productID").asText();
        int quantity = objectNode.get("quantity").asInt();

        ProductDto product = productService.getProductById(UUID.fromString(productID));

        if (product != null) {
            ProductMessage productMessage = Mapper.mapToProductMessage(product, quantity);
            productTemplate.convertAndSend(MQConfig.PRODUCT_EXCHANGE_TOPIC, MQConfig.PRODUCT_ROUTING_KEY, productMessage);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
