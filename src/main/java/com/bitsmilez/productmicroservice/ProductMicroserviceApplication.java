package com.bitsmilez.productmicroservice;

import com.bitsmilez.productmicroservice.core.domain.service.imp.ProductDto;
import com.bitsmilez.productmicroservice.core.domain.service.imp.ProductServiceImpl;
import com.bitsmilez.productmicroservice.core.domain.service.interfaces.IProductRepository;
import com.bitsmilez.productmicroservice.core.useCase.CreateProducts;
import com.bitsmilez.productmicroservice.port.csvAdapter.CSVAdapter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.FileNotFoundException;
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
	public CommandLineRunner demo(IProductRepository repository) throws FileNotFoundException {
		System.out.println("Create Products with CSV");
		String filePath = "/products.csv";
		List<ProductDto> products = CSVAdapter.readCsv(filePath);
		for (ProductDto product : products) {
			System.out.println(product);
		}

		//CSVAdapter csvAdapter = new CSVAdapter();
		//csvAdapter.getProducts("C:\\Users\\Biri\\Documents\\Uni\\KBE\\inconvenio-webshop-product-service\\products.csv");
		return (args) -> {

			CreateProducts create = new CreateProducts(new ProductServiceImpl(repository));
			create.createProducts();
		};
	}
}
