package com.mziuri.Product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.mziuri.Product.Product;

public class StorageConfig {
    private Product[] products;
    private static String password;
    @JsonCreator
    public StorageConfig(Product[] products, String password) {
        this.products = products;
        StorageConfig.password = password;
    }

    public StorageConfig() {
    }

    public Product[] getProducts() {
        return products;
    }

    public static String getPassword() {
        return password;
    }
}
