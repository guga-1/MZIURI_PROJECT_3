package com.mziuri.Product;

public class PurchaseRequest {
    private String name;
    private int amount;
    public PurchaseRequest(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
}
