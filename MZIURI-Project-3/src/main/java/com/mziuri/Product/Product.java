package com.mziuri.Product;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prod_id;
    @Column(name = "prod_name")
    private String prod_name;
    @Column(name = "prod_price")
    private float prod_price;
    @Column(name = "prod_amount")
    private int prod_amount;
    public int getProd_id() {
        return prod_id;
    }

    public String getProd_name() {
        return prod_name;
    }

    public float getProd_price() {
        return prod_price;
    }

    public int getProd_amount() {
        return prod_amount;
    }
    @JsonCreator
    public Product(int prod_id, String prod_name, float prod_price, int prod_amount) {
        this.prod_id = prod_id;
        this.prod_name = prod_name;
        this.prod_price = prod_price;
        this.prod_amount = prod_amount;
    }
}

