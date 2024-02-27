package com.mziuri.Product;

public class PurchaseResponse {
    private final String name;
    private final int remainingAmount;

    public String getName() {
        return name;
    }

    public int getRemainingAmount() {
        return remainingAmount;
    }

    public PurchaseResponse(String name, int remainingAmount) {
        this.name = name;
        this.remainingAmount = remainingAmount;
    }
}
