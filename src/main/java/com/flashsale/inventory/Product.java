package com.flashsale.inventory;

import java.util.Objects;

/**
 * Represents a product with its stock information
 */
public class Product {
    private final String productId;
    private final String productName;
    private final double price;
    
    public Product(String productId, String productName, double price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }
    
    public String getProductId() {
        return productId;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public double getPrice() {
        return price;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
    
    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }
}