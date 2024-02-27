package com.mziuri.Product;

public class AddProductRequest extends StorageConfig {
    private static String Password;
    private static String Name;
    private static int Amount;

    public static String getPassword() {
        return Password;
    }

    public static String getName() {
        return Name;
    }

    public static int getAmount() {
        return Amount;
    }

    public AddProductRequest(String password, String name, int amount) {
        this.Password = Password;
        this.Name = Name;
        this.Amount = Amount;
    }
}
