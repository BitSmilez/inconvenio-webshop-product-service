package com.bitsmilez.productmicroservice.port.MQAdapter;

import com.bitsmilez.productmicroservice.config.MQConfig;
import com.bitsmilez.productmicroservice.config.ProductMessage;
import com.bitsmilez.productmicroservice.core.domain.service.imp.ProductDto;
import com.bitsmilez.productmicroservice.core.domain.service.interfaces.IProductService;
import com.bitsmilez.productmicroservice.port.mapper.Mapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8089")
@RestController("/cart")
public class ProductProducer {

    @Autowired
    private RabbitTemplate productTemplate;
    @Autowired
    private IProductService productService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductProducer.class);


    public ProductProducer(IProductService productService, RabbitTemplate productTemplate) {
        super();
        this.productService = productService;
        this.productTemplate = productTemplate;
    }

    @PostMapping("/add-to-cart")
    public ResponseEntity<?> publishAddToCartEvent(@RequestBody ObjectNode objectNode) {
        String productID = objectNode.get("productID").asText();
        int quantity = objectNode.get("quantity").asInt();

        ProductDto product = productService.getProductById(UUID.fromString(productID));

        if (product != null) {
            ProductMessage productMessage = Mapper.mapToProductMessage(product, quantity);
            LOGGER.info(String.format("Add Message sent -> %s", productMessage));
            productTemplate.convertAndSend(MQConfig.PRODUCT_EXCHANGE, MQConfig.PRODUCT_TOPIC_ADD_TO_CART, productMessage);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/remove-from-cart")
    public ResponseEntity<?> publishRemoveFromCartEvent(@RequestBody ObjectNode objectNode) {
        String productID = objectNode.get("productID").asText();
        String cartID = objectNode.get("cartID").asText();

        if (productID != null && cartID != null) {
            ProductMessage productMessage = new ProductMessage();
            productMessage.setProductID(productID);
            productMessage.setUserID(cartID);

            LOGGER.info(String.format("Remove Message sent -> %s", productMessage));

            productTemplate.convertAndSend(MQConfig.PRODUCT_EXCHANGE, MQConfig.PRODUCT_TOPIC_REMOVE_FROM_CART, productMessage);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/update-cart")
    public ResponseEntity<?> publishUpdateCartEvent(@RequestBody ObjectNode objectNode) {
        String productID = objectNode.get("productID").asText();
        int quantity = objectNode.get("quantity").asInt();
        String cartID = objectNode.get("cartID").asText();

        if (productID != null && cartID != null) {
            ProductMessage productMessage = new ProductMessage();
            productMessage.setProductID(productID);
            productMessage.setUserID(cartID);
            productMessage.setQuantity(quantity);

            LOGGER.info(String.format("Update Message sent -> %s", productMessage));

            productTemplate.convertAndSend(MQConfig.PRODUCT_EXCHANGE, MQConfig.PRODUCT_TOPIC_UPDATE_CART, productMessage);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
