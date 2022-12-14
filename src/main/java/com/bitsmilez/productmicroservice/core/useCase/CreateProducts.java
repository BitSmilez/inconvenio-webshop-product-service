package com.bitsmilez.productmicroservice.core.useCase;

import com.bitsmilez.productmicroservice.core.domain.model.Categories;
import com.bitsmilez.productmicroservice.core.domain.service.imp.ProductDto;
import com.bitsmilez.productmicroservice.core.domain.service.interfaces.IProductService;

import java.util.LinkedList;


public class CreateProducts {


    IProductService productService;
    

    public CreateProducts(IProductService productService) {
        this.productService = productService;
    }

    public  void createProducts(){
        LinkedList<ProductDto> products = new LinkedList<>();

        ProductDto boots = new ProductDto();
        boots.setName("The best boots you ever saw");
        boots.setPrice(420.);
        boots.setSalesPrice(69.69);
        boots.setImg("https://ucarecdn.com/81152736-d006-4fbc-8582-a60f0f8d0683/");
        boots.setDescription("I sure hope it wont rain when you wear these awesome boots!");
        boots.setStock(5);
        boots.setCategory(Categories.Cloths);
        //entityManager.persist(boots);

        ProductDto mug = new ProductDto();
        mug.setName("THE mug");
        mug.setPrice(420.);
        mug.setSalesPrice(69.69);
        mug.setImg("https://ucarecdn.com/8f73e3b8-bc9c-46d1-a0f7-1bad02629692/");
        mug.setDescription("I dont think its a good idea to wear a white shirt with that one buddy.");
        mug.setStock(5);
        mug.setCategory(Categories.Tableware);
        //entityManager.persist(mug);



        ProductDto wineGlass = new ProductDto();
        wineGlass.setName("Best wine glass ever");
        wineGlass.setPrice(420.);
        wineGlass.setSalesPrice(69.69);
        wineGlass.setImg("https://ucarecdn.com/ba2add07-bb0e-4e23-8081-cd90b936aa95/");
        wineGlass.setDescription("Brother if you buy that, you got some problems.");
        wineGlass.setStock(5);
        wineGlass.setCategory(Categories.Tableware);
        //entityManager.persist(wineGlass);


        ProductDto champagneGlass = new ProductDto();
        champagneGlass.setName("Lets have champagne glass");
        champagneGlass.setPrice(420.);
        champagneGlass.setSalesPrice(69.69);
        champagneGlass.setImg("https://ucarecdn.com/9f95c6c4-ace3-4f59-a0da-e0c15591e002/");
        champagneGlass.setDescription("For our fellow buddy with two mouths (or dumbasses)");
        champagneGlass.setStock(5);
        champagneGlass.setCategory(Categories.Tableware);
        //entityManager.persist(champagneGlass);


        ProductDto fork = new ProductDto();
        fork.setName("I never wanna use a diffrent fork FORK!");
        fork.setPrice(420.);
        fork.setSalesPrice(69.69);
        fork.setImg("https://ucarecdn.com/ac45e974-a77e-4f1c-86d1-f189204d18df/");
        fork.setDescription("I sure hope it wont rain when you wear these awesome spoon!");
        fork.setStock(5);
        fork.setCategory(Categories.Tableware);

        products.add(fork);
        products.add(mug);
        products.add(champagneGlass);
        products.add(boots);
        products.add(wineGlass);

        products.forEach(productDto -> productService.createProduct(productDto));


    }
}
