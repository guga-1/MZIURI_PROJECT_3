package com.mziuri.Product;

public class GetProductInfoResponse {
    private String name;
    private float price;
    private int amount;

    public GetProductInfoResponse(String name, float price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }
}
