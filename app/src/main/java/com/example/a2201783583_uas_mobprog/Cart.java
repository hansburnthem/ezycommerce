package com.example.a2201783583_uas_mobprog;

public class Cart {

    private Integer bookId, qty;
    private Double price;
    private String name, category, author, type, img;

    private String desc;

    //init constructor
    public Cart(Integer bookId, Double price, String name, String category, String desc, String author, String type, String img, Integer qty) {
        this.bookId = bookId;
        this.price = price;
        this.name = name;
        this.category = category;
        this.desc = desc;
        this.author = author;
        this.type = type;
        this.img = img;
        this.qty = qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getBookId() {
        return bookId;
    }

    public Integer getQty() {
        return qty;
    }

    public Double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getAuthor() {
        return author;
    }

    public String getType() {
        return type;
    }

    public String getImg() {
        return img;
    }

    public String getDesc() {
        return desc;
    }
}
