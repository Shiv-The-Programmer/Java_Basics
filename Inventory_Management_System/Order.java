package OOPS_Practice.Inventory_Management_System;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {
    private String orderID;
    private List<Product> orderedProducts;
    private double totalCost;
    private String orderStatus;

    // Constructor
    public Order(String orderID, List<Product> orderedProducts, double totalCost, String orderStatus) {
        this.orderID = orderID;
        this.orderedProducts = orderedProducts;
        this.totalCost = totalCost;
        this.orderStatus = orderStatus;
    }

    // Getter and Setter methods for orderID
    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    // Getter and Setter methods for orderedProducts
    public List<Product> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(List<Product> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    // Getter and Setter methods for totalCost
    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    // Getter and Setter methods for orderStatus
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    // Method to add a product to the order
    public void addProduct(Product product) {
        // Add the product to the list of ordered products
        // Update the total cost
        orderedProducts.add(product);
        calculateTotalCost();
    }

    // Method to remove a product from the order
    public void removeProduct(Product product) {
        // Remove the product from the list of ordered products
        // Update the total cost
        orderedProducts.remove(product);
        calculateTotalCost();
    }

    // Method to calculate the total cost of the order
    private void calculateTotalCost() {
        // Iterate through the list of ordered products and sum up their prices
        // Update the total cost
        double total = 0.0;
        for (Product product : orderedProducts) {
            total += product.getPrice();
        }
        // Update the total cost
        this.totalCost = total;
    }

    // Method to update the order status
    public void updateOrderStatus(String status) {
        // Update the order status
        this.orderStatus = status;
        System.out.println("Status of the order is " + this.orderStatus);
    }
}
