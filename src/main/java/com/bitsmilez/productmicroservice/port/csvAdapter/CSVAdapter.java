package com.bitsmilez.productmicroservice.port.csvAdapter;
import com.bitsmilez.productmicroservice.core.domain.service.imp.ProductDto;
import com.google.gson.Gson;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class CSVAdapter {
    public String getProducts(String filename) throws FileNotFoundException {

        List<ProductDto> products = new CsvToBeanBuilder<ProductDto>(new FileReader(filename))
                .withType(ProductDto.class)
                .build()
                .parse();
        return new Gson().toJson(products);
    }
}
