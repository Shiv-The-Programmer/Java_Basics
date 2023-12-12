package OOPS_Practice.Inventory_Management_System;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Inventory implements Serializable {
    private List<Product> productsInStock;

    // Constructor, methods to add, remove, and display products, etc.

    public Inventory(List<Product> productsInStock) {
        this.productsInStock = productsInStock;
    }

    // Method to add a new product to the inventory
    public void addProduct(Product product) {
        // Add the product to the list of products in stock
        productsInStock.add(product);

    }

    // Method to remove a product from the inventory
    public void removeProduct(String productID) {
        // Remove the product with the specified productID from the list
        for(Product product: productsInStock){
            if(productID.equals(product.getProductID())){
                productsInStock.remove(product);
                return;
            }
        }
        System.out.println("No such product with product ID => " + productID + "exists in stock");
    }

    // Method to display details of all products in stock
    public void displayProductsInStock() {
        // Iterate through the list of products and display details
        for (Product product : productsInStock) {
            System.out.println("product ID: " + product.getProductID());
            System.out.println("product name: " + product.getProductName());
            System.out.println("product price: " + product.getPrice());
            System.out.println("about this product " + product.getDescription());
            System.out.println("number of such products available " + product.getQuantityInStock());
            System.out.println("--------------");
        }
    }

    // Method to search for a product by productID and display its details
    public void searchProduct(String productID) {
        // Search for the product with the specified productID and display details
        for(Product product: productsInStock) {
            if (productID.equals(product.getProductID())) {
                System.out.println("product name: " + product.getProductName());
                System.out.println("product price: " + product.getPrice());
            }
        }
        System.out.println("No such product with product ID => " + productID + "exists in stock");
    }

    // Method to get the quantity of a product in the inventory
    public int getProductQuantity(String productID) {
        for (Product product : productsInStock) {
            if (productID.equals(product.getProductID())) {
                return product.getQuantityInStock();
            }
        }
        return 0; // Return 0 if the product is not found (you can choose an appropriate default value)
    }
    // Method to update the quantity of a product in the inventory
    public void updateProductQuantity(String productID, int newQuantity) {
        for (Product product : productsInStock) {
            if (productID.equals(product.getProductID())) {
                product.setQuantityInStock(newQuantity);
                return; // Assuming each product ID is unique; if not, consider handling duplicates
            }
        }
        // Handle the case where the product is not found in the inventory
        System.out.println("Error: Product with ID " + productID + " not found in stock.");
    }

    // Method to check the availability of a product in stock
    public boolean isProductAvailable(String productID) {
        // Check if the product with the specified productID is available
        // Return true if available, false otherwise
        for(Product product: productsInStock) {
            if (productID.equals(product.getProductID())) {
                return true;
            }
        }
        return false;
    }

//    private List<Product> productsInStock;

    // Existing methods...

    // Method to get the list of products in the inventory
    public List<Product> getProductsInStock() {
        return productsInStock;
    }
    public void setProductsInStock(List<Product> productsInStock) {
        this.productsInStock = productsInStock;
    }

    // Method to get a product by its ID
    public Product getProductByID(String productID) {
        for (Product product : productsInStock) {
            if (productID.equals(product.getProductID())) {
                return product;
            }
        }
        return null;  // Return null if the product with the specified ID is not found
    }

}
