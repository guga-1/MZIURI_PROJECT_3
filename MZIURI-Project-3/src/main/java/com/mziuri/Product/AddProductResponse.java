package com.mziuri.Product;

public class AddProductResponse {
    private String name;
    private int remainingAmount;

    public AddProductResponse(String name, int remainingAmount) {
        this.name = name;
        this.remainingAmount = remainingAmount;
    }

    public String getName() {
        return name;
    }

    public int getRemainingAmount() {
        return remainingAmount;
    }
}
