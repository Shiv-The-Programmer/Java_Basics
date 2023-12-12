package OOPS_Practice.Inventory_Management_System;
import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {
    private String productID;
    private String productName;
    private String description;
    private double price;
    private int quantityInStock;

    //private List<Product> ProductsListInStock;

    // Constructor, getters, setters...

    public Product(String productID, String productName, String description, double price, int quantityInStock) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }


}

