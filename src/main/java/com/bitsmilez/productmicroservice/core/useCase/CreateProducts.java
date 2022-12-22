package com.bitsmilez.productmicroservice.core.useCase;

import com.bitsmilez.productmicroservice.core.domain.model.Categories;
import com.bitsmilez.productmicroservice.core.domain.service.imp.ProductDto;
import com.bitsmilez.productmicroservice.core.domain.service.interfaces.IProductService;

import java.math.BigDecimal;
import java.util.LinkedList;


public class CreateProducts {


    IProductService productService;


    public CreateProducts(IProductService productService) {
        this.productService = productService;
    }

    public void createProducts() {
        LinkedList<ProductDto> products = new LinkedList<>();


        ProductDto boots = new ProductDto();
        boots.setName("Incredible Boots.");
        boots.setPrice(new BigDecimal("1337.00"));
        boots.setSalesPrice(null);
        boots.setImg("https://ucarecdn.com/81152736-d006-4fbc-8582-a60f0f8d0683/");
        boots.setDescription("I sure hope it wont rain when you wear these awesome boots!");
        boots.setDetailedDescription("I sure hope it wont rain when you wear these awesome boots! " +
                "These boots are so awesome that you will be the envy of all your friends. They are made " +
                "of the finest leather and are so comfortable that you will never want to take them off. " +
                "They are so awesome that you will be the envy of all your friends. They are made of the finest leather " +
                "and are so comfortable that you will never want to take them off.");
        boots.setDesigner("Mario Doe");
        boots.setProductOrigin("Italy");
        boots.setMaterial("Leather");
        boots.setStock(5);
        boots.setCategory(Categories.Clothes);
        //entityManager.persist(boots);

        ProductDto mug = new ProductDto();
        mug.setName("THE(!) Mug");
        mug.setPrice(new BigDecimal("400.00"));
        mug.setSalesPrice(new BigDecimal("69.95"));
        mug.setImg("https://ucarecdn.com/8f73e3b8-bc9c-46d1-a0f7-1bad02629692/");
        mug.setDescription("I dont think its a good idea to wear a white shirt with that one buddy.");
        mug.setDetailedDescription("I dont think its a good idea to wear a white shirt with that one buddy. " +
                "This is the best mug you will ever own. It is made of the finest ceramic and is so awesome that you will " +
                "never want to drink from any other mug. It is so awesome that you will be the envy of all your friends. ");
        mug.setDesigner("Not Me");
        mug.setProductOrigin("Germany");
        mug.setMaterial("Ceramic");
        mug.setStock(5);
        mug.setCategory(Categories.Tableware);
        //entityManager.persist(mug);


        ProductDto wineGlass = new ProductDto();
        wineGlass.setName("Wine Glass with a twist");
        wineGlass.setPrice(new BigDecimal("420.00"));
        wineGlass.setSalesPrice(new BigDecimal("2.00"));
        wineGlass.setImg("https://ucarecdn.com/ba2add07-bb0e-4e23-8081-cd90b936aa95/");
        wineGlass.setDescription("Brother if you buy that, you got some problems.");
        wineGlass.setDetailedDescription("Brother if you buy that, you got some problems. " +
                "This is the best wine glass you will ever own. It is made of the finest glass and is so awesome that you will " +
                "never want to drink from any other wine glass. It is so awesome that you will be the envy of all your friends. ");
        wineGlass.setDesigner("Katerina Karen");
        wineGlass.setProductOrigin("Prague");
        wineGlass.setMaterial("Glass");
        wineGlass.setStock(5);
        wineGlass.setCategory(Categories.Tableware);
        //entityManager.persist(wineGlass);


        ProductDto champagneGlass = new ProductDto();
        champagneGlass.setName("Champagne for Two(TM)");
        champagneGlass.setPrice(new BigDecimal("99.99"));
        boots.setSalesPrice(null);
        champagneGlass.setImg("https://ucarecdn.com/9f95c6c4-ace3-4f59-a0da-e0c15591e002/");
        champagneGlass.setDescription("For our fellow buddy with two mouths (or dumbasses)");
        champagneGlass.setDetailedDescription("For our fellow buddy with two mouths. This is the best champagne glass you will ever own. " +
                "It is made of the finest glass and is so awesome that you will " +
                "never want to drink from any other champagne glass. It is so awesome that you will be the envy of all your friends. ");
        champagneGlass.setDesigner("Piet Hein");
        champagneGlass.setProductOrigin("Denmark");
        champagneGlass.setMaterial("Glass");
        champagneGlass.setStock(5);
        champagneGlass.setCategory(Categories.Tableware);
        //entityManager.persist(champagneGlass);


        ProductDto fork = new ProductDto();
        fork.setName("I never wanna use a different fork again");
        fork.setPrice(new BigDecimal("199.99"));
        fork.setSalesPrice(new BigDecimal("9.99"));
        fork.setImg("https://ucarecdn.com/ac45e974-a77e-4f1c-86d1-f189204d18df/");
        fork.setDescription("I sure hope it wont rain when you use this awesome fork!");
        fork.setDetailedDescription("I sure hope it wont rain when you use this awesome fork! " +
                "This Fork is so awesome that you will be the envy of all your friends. It is made " +
                "of the finest metal and is so comfortable that you will never want to put it back. " +
                "It is so awesome that you will be the envy of all your friends. It is literally made of the finest metal " +
                "and is so comfortable that you will never want to put it back. No Joke! No. I am not repeating myself.");
        fork.setDesigner("Biri The Rock");
        fork.setProductOrigin("Korea");
        fork.setMaterial("Metal");
        fork.setStock(5);
        fork.setCategory(Categories.Cutlery);

        products.add(fork);
        products.add(mug);
        products.add(champagneGlass);
        products.add(boots);
        products.add(wineGlass);

        products.forEach(productDto -> productService.createProduct(productDto));


    }
}
