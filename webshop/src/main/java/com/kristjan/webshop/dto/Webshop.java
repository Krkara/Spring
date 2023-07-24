package com.kristjan.webshop.dto;

import java.util.ArrayList;

@lombok.Data
public class Webshop {
    public Shop shop;
}

@lombok.Data
class Shop {
    public ArrayList<Product> products;
    public int total;
    public int skip;
    public int limit;
}
@lombok.Data
class Product{
    public int id;
    public String title;
    public String description;
    public int price;
    public double discountPercentage;
    public double rating;
    public int stock;
    public String brand;
    public String category;
    public String thumbnail;
    public ArrayList<String> images;
}


