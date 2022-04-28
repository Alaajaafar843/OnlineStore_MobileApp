package com.hell.buildingsupplies;

import org.jetbrains.annotations.NotNull;

public class Product {

    private final int pid;
    private final String productName;
    private final String categoryName;
    private final int quantity;
    private final double price;


    public Product(int pid, String productName, int quantity, double price , String categoryName) {
        this.pid = pid;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getPid() {
        return pid;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    @NotNull
    @Override
    public String toString() {
        return  "id:" + pid +", product'" + productName + ", quantity: " + quantity +", price: " + price+", category: "+categoryName;
    }
}
