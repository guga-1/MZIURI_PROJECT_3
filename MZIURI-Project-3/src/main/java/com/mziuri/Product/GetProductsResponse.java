package com.mziuri.Product;

public class GetProductsResponse {
    private String[] productNames;

    public GetProductsResponse(String[] productNames) {
        this.productNames = productNames;
    }

    public String[] getProductNames() {
        return productNames;
    }
}
