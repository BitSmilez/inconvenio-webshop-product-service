package com.bitsmilez.productmicroservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class ProductMicroserviceApplication implements CommandLineRunner {


	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


	public static void main(String[] args) {
		//Step1
		SpringApplication.run(ProductMicroserviceApplication.class, args);
		//Step3

	}

	@Override
	public void run(String... args) throws Exception {
		//step2


	}
}
