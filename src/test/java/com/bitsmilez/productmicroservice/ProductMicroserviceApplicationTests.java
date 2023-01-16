package com.bitsmilez.productmicroservice;

import com.bitsmilez.productmicroservice.port.productAdapter.ProductController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductMicroserviceApplicationTests {
	@Autowired
	private ProductController productController;


	@Test
	void contextLoads() {
	}

	@Test
	void testProductController() {
		assert productController != null;
	}



}
