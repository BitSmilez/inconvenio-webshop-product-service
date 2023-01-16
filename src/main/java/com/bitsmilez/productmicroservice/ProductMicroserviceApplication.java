package com.bitsmilez.productmicroservice;

import com.bitsmilez.productmicroservice.core.domain.service.imp.ProductDto;
import com.bitsmilez.productmicroservice.core.domain.service.interfaces.IProductRepository;
import com.bitsmilez.productmicroservice.port.csvAdapter.CSVAdapter;
import com.bitsmilez.productmicroservice.port.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

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
		String filePath = "/app/products.csv";
		List<ProductDto> products = CSVAdapter.readCsv(filePath);
		for (ProductDto product : products) {
			System.out.println(Mapper.mapToProduct(product));
		}


		return (args) -> {
			// save a few customers
			repository.saveAll(products.stream().map(Mapper::mapToProduct).toList());
		};

	}
}
