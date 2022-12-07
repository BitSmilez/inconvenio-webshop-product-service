CREATE DATABASE PRODUCTSERVICE;


create TABLE PRODUCT (
                         ID float8 not NULL ,
                         ArticleName varchar (255) not NULL ,
                         ArticleDescription varchar (3000),
                         Picture varchar (50),
                         PRICE float8 not NULL
);