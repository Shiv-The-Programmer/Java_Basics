package OOPS_Practice.Inventory_Management_System;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class InventoryManagementSystem {
    private List<Customer> customers;
    private Inventory inventory;

    private Product product;

    // Constructor, methods for customer registration, order placement, data loading/saving, etc.

    public InventoryManagementSystem(List<Customer> customers, Inventory inventory) {
        this.customers = customers;
        this.inventory = inventory;
    }

    // Getter method for the list of customers
    public List<Customer> getCustomers() {
        return customers;
    }

    // Getter method for the inventory
    public Inventory getInventory() {
        return inventory;
    }

    // Method to register a new customer
    public void registerCustomer(Customer customer) {
        // Add the new customer to the list of customers
        customers.add(customer);
    }

    // Method to display all registered customers
    public void displayCustomers() {
        // Iterate through the list of customers and display their details
        for(Customer customer: customers){
            System.out.println("Customer ID " + customer.getCustomerID());
            System.out.println("Customer name " + customer.getCustomerName());
            System.out.println("Customer email ID" + customer.getEmail());
            System.out.println("--------------");
        }
    }

    // Method to place an order for a customer
    public void placeOrder(Customer customer, Order order) {
        // Add the order to the customer's order history
        customer.placeOrder(order);
        // Update inventory based on the order
        List<Product> orderedProducts = order.getOrderedProducts();
        for (Product orderedProduct : orderedProducts) {
            // Check if the ordered product is available in the inventory
            boolean isProductAvailable = inventory.isProductAvailable(orderedProduct.getProductID());

            if (isProductAvailable) {
                // Update the quantity of the product in the inventory
                int currentQuantity = inventory.getProductQuantity(orderedProduct.getProductID());
                inventory.updateProductQuantity(orderedProduct.getProductID(), currentQuantity - 1);
            } else {
                // Handle the case where the ordered product is not available in the inventory
                System.out.println("Error: Product with ID " + orderedProduct.getProductID() + " is not available in stock.");
                // You might want to consider rolling back the order or taking appropriate actions
            }
        }

    }

    // Method to display products in stock from the inventory
    public void displayProductsInStock() {
        // Call the corresponding method in the inventory
        inventory.displayProductsInStock();
    }

    public void saveDataToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(customers);
            oos.writeObject(inventory.getProductsInStock());
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public void loadDataFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            List<Customer> loadedCustomers = (List<Customer>) ois.readObject();
            List<Product> loadedProducts = (List<Product>) ois.readObject();

            customers.addAll(loadedCustomers);
            inventory.setProductsInStock(loadedProducts);

            System.out.println("Data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    // Additional methods for other functionality...

}
