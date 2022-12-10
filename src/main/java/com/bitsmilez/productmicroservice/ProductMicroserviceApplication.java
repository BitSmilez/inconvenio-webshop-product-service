package com.bitsmilez.productmicroservice;

import com.bitsmilez.productmicroservice.core.domain.service.imp.ProductServiceImpl;
import com.bitsmilez.productmicroservice.core.domain.service.interfaces.IProductRepository;
import com.bitsmilez.productmicroservice.core.useCase.CreateProducts;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.bitsmilez.productmicroservice.core.domain.service.interfaces")
public class ProductMicroserviceApplication  {


	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


	public static void main(String[] args) {
		//Step1
		SpringApplication.run(ProductMicroserviceApplication.class, args);
		//Step3

	}

	@Bean
	public CommandLineRunner demo(IProductRepository repository) {
		System.out.println("Create Products!");
		return (args) -> {
			CreateProducts create = new CreateProducts(new ProductServiceImpl(repository));
			create.createProducts();
		};
	}
}
