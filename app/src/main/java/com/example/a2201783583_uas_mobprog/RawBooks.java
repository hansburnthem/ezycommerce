package com.example.a2201783583_uas_mobprog;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RawBooks {
    private Integer statusCode;
    private String nim, productId, credits;

    @SerializedName("nama")
    private String name;

    @SerializedName("products")
    private ArrayList<Book> books = new ArrayList<>();

    public RawBooks(Integer statusCode, String nim, String productId, String credits, String name, ArrayList<Book> books) {
        this.statusCode = statusCode;
        this.nim = nim;
        this.productId = productId;
        this.credits = credits;
        this.name = name;
        this.books = books;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getNim() {
        return nim;
    }

    public String getProductId() {
        return productId;
    }

    public String getCredits() {
        return credits;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }
}
