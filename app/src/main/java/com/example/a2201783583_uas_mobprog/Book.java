package com.example.a2201783583_uas_mobprog;

import com.google.gson.annotations.SerializedName;

public class Book {
    @SerializedName("id")
    private Integer bookId;
    private Double price;
    private String name, category, author, type, img;
    private boolean inCart;

    @SerializedName("description")
    private String desc;

    //init constructor
    public Book(Integer bookId, Double price, String name, String category, String desc, String author, String type, String img) {
        this.bookId = bookId;
        this.price = price;
        this.name = name;
        this.category = category;
        this.desc = desc;
        this.author = author;
        this.type = type;
        this.img = img;
    }

    //init getter setter
    public boolean isInCart() {
        return inCart;
    }

    public void setInCart(boolean inCart) {
        this.inCart = inCart;
    }

    public Integer getId() {
        return bookId;
    }

    public void setId(Integer bookId) {
        this.bookId = bookId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
