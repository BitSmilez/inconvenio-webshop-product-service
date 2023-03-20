package com.bitsmilez.productmicroservice.port.csvAdapter;

import com.bitsmilez.productmicroservice.core.domain.model.Categories;
import com.bitsmilez.productmicroservice.core.domain.service.imp.ProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CSVAdapterTest {

    @TempDir
    static Path tempDir;

    @Test
    public void readCsv_shouldReturnListOfProducts() throws IOException {
        // Arrange
        String[] productsData = {
                "Name;Price;SalesPrice;Img;Description;DetailedDescription;Designer;ProductOrigin;Material;Stock;Category",
                "Product 1;10.00;;img1.jpg;Description 1;Detailed Description 1;Designer 1;Product Origin 1;Material 1;100;Tableware",
                "Product 2;20.00;18.00;img2.jpg;Description 2;Detailed Description 2;Designer 2;Product Origin 2;Material 2;200;Tableware"
        };
        Path csvFilePath = Paths.get(tempDir.toString(), "test.csv");
        Files.write(csvFilePath, List.of(productsData));

        // Act
        List<ProductDto> result = CSVAdapter.readCsv(csvFilePath.toString());

        // Assert
        Assertions.assertEquals(2, result.size());

        // Check the first product
        ProductDto product1 = result.get(0);
        Assertions.assertNotNull(product1.getId());
        Assertions.assertEquals("Product 1", product1.getName());
        Assertions.assertEquals(new BigDecimal("10.00"), product1.getPrice());
        Assertions.assertNull(product1.getSalesPrice());
        Assertions.assertEquals("img1.jpg", product1.getImg());
        Assertions.assertEquals("Description 1", product1.getDescription());
        Assertions.assertEquals("Detailed Description 1", product1.getDetailedDescription());
        Assertions.assertEquals("Designer 1", product1.getDesigner());
        Assertions.assertEquals("Product Origin 1", product1.getProductOrigin());
        Assertions.assertEquals("Material 1", product1.getMaterial());
        Assertions.assertEquals(100, product1.getStock());
        Assertions.assertEquals(Categories.Tableware, product1.getCategory());

        // Check the second product
        ProductDto product2 = result.get(1);
        Assertions.assertNotNull(product2.getId());
        Assertions.assertEquals("Product 2", product2.getName());
        Assertions.assertEquals(new BigDecimal("20.00"), product2.getPrice());
        Assertions.assertEquals(new BigDecimal("18.00"), product2.getSalesPrice());
        Assertions.assertEquals("img2.jpg", product2.getImg());
        Assertions.assertEquals("Description 2", product2.getDescription());
        Assertions.assertEquals("Detailed Description 2", product2.getDetailedDescription());
        Assertions.assertEquals("Designer 2", product2.getDesigner());
        Assertions.assertEquals("Product Origin 2", product2.getProductOrigin());
        Assertions.assertEquals("Material 2", product2.getMaterial());
        Assertions.assertEquals(200, product2.getStock());
        Assertions.assertEquals(Categories.Tableware, product2.getCategory());
    }

    @Test
    public void readCsv_shouldReturnEmptyList_whenCsvFileIsEmpty() throws IOException {
        // Arrange
        Path csvFilePath = Paths.get(tempDir.toString(), "test.csv");
        Files.createFile(csvFilePath);

        // Act
        List<ProductDto> result = CSVAdapter.readCsv(csvFilePath.toString());

        // Assert
        Assertions.assertEquals(0, result.size());
    }

}
