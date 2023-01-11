package com.bitsmilez.productmicroservice.port.csvAdapter;

import com.bitsmilez.productmicroservice.core.domain.model.Categories;
import com.bitsmilez.productmicroservice.core.domain.service.imp.ProductDto;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CSVAdapter {

    public static List<ProductDto> readCsv(String csvFile) {
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ";";
        List<ProductDto> products = new ArrayList<>();
        int i = 0;
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                System.out.println("erste Zeile jo | ");
                if(i==0){
                    i++;
                    continue;
                }

                // use comma as separator
                String[] productData = line.split(cvsSplitBy);

                UUID id = UUID.randomUUID();
                String name = productData[0];
                System.out.println("hier huhu"+ Arrays.toString(productData));

                BigDecimal price = new BigDecimal(productData[1]);
                BigDecimal salesPrice = null;
                if (!productData[2].equals("")){
                     salesPrice = new BigDecimal(productData[2]);

                }

                String img = productData[3];
                String description = productData[4];
                String detailedDescription = productData[5];
                String designer = productData[6];
                String productOrigin = productData[7];
                String material = productData[8];
                int stock = Integer.parseInt(productData[9]);
                Categories category = Categories.valueOf(productData[10]);

                ProductDto product = new ProductDto(id, name, price, salesPrice, img, description, detailedDescription, designer, productOrigin, material, stock, category);
                products.add(product);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return products;
    }
}