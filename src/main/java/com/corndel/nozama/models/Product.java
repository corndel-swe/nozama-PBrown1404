package com.corndel.nozama.models;

public class Product {
    private Integer id;
    private String name;
    private String description;
    private Float price;
    private Integer stockQuantity;
    private String imageURL;

    public Product(Integer id, String name, String description, Float price, Integer stockQuantity, String imageURL)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.imageURL = imageURL;
    }

    public Integer getId() {return id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public Float getPrice() {return price;}

    public void setPrice(Float price) {this.price = price;}

    public Integer getStockQuantity() {return stockQuantity;}

    public void setStockQuantity(Integer stockQuantity) {this.stockQuantity = stockQuantity;}

    public String getImageURL() {return imageURL;}

    public void setImageURL(String imageURL) {this.imageURL = imageURL;}

}
