package OOPS_Practice.Inventory_Management_System;

import java.util.Scanner;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        // Initialize Inventory Management System
        InventoryManagementSystem inventoryManagementSystem = initializeSystem();

        // Run the Inventory Management System
        runInventoryManagementSystem(inventoryManagementSystem);
    }

    // Method to initialize the Inventory Management System
    private static InventoryManagementSystem initializeSystem() {
        // Initialize customers
        List<Customer> customers = new ArrayList<>();

        // Sample customers
        Customer customer1 = new Customer("C001", "John Doe", "john@example.com", new ArrayList<>());
        Customer customer2 = new Customer("C002", "Jane Smith", "jane@example.com", new ArrayList<>());

        customers.add(customer1);
        customers.add(customer2);

        // Initialize products
        List<Product> products = new ArrayList<>();

        // Sample products
        Product product1 = new Product("P001", "Laptop", "Powerful laptop with high performance", 999.99, 10);
        Product product2 = new Product("P002", "Smartphone", "Latest smartphone with advanced features", 499.99, 20);

        products.add(product1);
        products.add(product2);

        // Initialize inventory
        Inventory inventory = new Inventory(products);

        // Return a new InventoryManagementSystem instance with initialized data
        return new InventoryManagementSystem(customers, inventory);
    }

    // Method to run the Inventory Management System
    private static void runInventoryManagementSystem(InventoryManagementSystem ims) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            // Display menu options
            displayMenu();

            // Get user choice
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Process user choice
            switch (choice) {
                case 1:
                    // Register a new customer
                    registerCustomer(ims, scanner);
                    break;
                case 2:
                    // Place an order
                    placeOrder(ims, scanner);
                    break;
                case 3:
                    // Display products in stock
                    ims.displayProductsInStock();
                    break;
                case 4:
                    // Display all registered customers
                    ims.displayCustomers();
                    break;
                case 5:
                    // Save data to file
                    saveDataToFile(ims, scanner);
                    break;
                case 6:
                    // Load data from file
                    loadDataFromFile(ims, scanner);
                    break;
                case 7:
                    // Exit the program
                    System.out.println("Exiting Inventory Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 7);

        // Close the scanner
        scanner.close();
    }

    // Other helper methods for menu options...

    // Example: Method to register a new customer
    private static void registerCustomer(InventoryManagementSystem ims, Scanner scanner) {
        // Collect customer details from the user
        // Create a new Customer object
        // Register the new customer in the system
        // Display a success message
        System.out.println("Enter your name ");
        String customer_name = scanner.nextLine();
        System.out.println("Enter your email ");
        String customer_email_id = scanner.next();
        List<Order> orderHistory = new ArrayList<>();
        String customerID = "C" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        Customer customer = new Customer(customerID, customer_name, customer_email_id, orderHistory);

        ims.registerCustomer(customer);
        System.out.println("Customer registered successfully with ID: " + customerID);
    }

    // Example: Method to place an order
    private static void placeOrder(InventoryManagementSystem ims, Scanner scanner) {
        // Collect order details from the user
        System.out.println("Enter your customer ID: ");
        String customerID = scanner.next();  // Assuming the user provides the customer ID

        // Check if the customer with the provided ID exists
        Customer customer = findCustomerByID(ims, customerID);

        if (customer != null) {
            // Create a new Order object
            List<Product> orderedProducts = selectProducts(scanner, ims);  // Implement this method to select products
            double totalCost = calculateTotalCost(orderedProducts);  // Implement this method to calculate the total cost

            String orderID = "O" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
            String orderStatus = "Placed";  // You can set an initial status

            Order order = new Order(orderID, orderedProducts, totalCost, orderStatus);

            // Place the order in the system
            ims.placeOrder(customer, order);

            // Display a success message
            System.out.println("Order placed successfully with ID: " + orderID);
        } else {
            System.out.println("Customer with ID " + customerID + " not found.");
        }
    }

    // Helper method to find a customer by ID
    private static Customer findCustomerByID(InventoryManagementSystem ims, String customerID) {
        for (Customer customer : ims.getCustomers()) {
            if (customer.getCustomerID().equals(customerID)) {
                return customer;
            }
        }
        return null;
    }

    // Helper method to select products for the order
    private static List<Product> selectProducts(Scanner scanner, InventoryManagementSystem ims) {
        List<Product> selectedProducts = new ArrayList<>();

        // Display available products from the inventory
        ims.getInventory().displayProductsInStock();

        // Prompt the user to select products
        System.out.println("Enter product IDs (comma-separated) to select products:");
        String input = scanner.next();

        // Split the user input to get individual product IDs
        String[] selectedProductIDs = input.split(",");

        // Validate and add selected products to the list
        for (String productID : selectedProductIDs) {
            if (ims.getInventory().isProductAvailable(productID)) {
                // Retrieve the product from the inventory and add it to the selected products list
                Product selectedProduct = ims.getInventory().getProductByID(productID);
                selectedProducts.add(selectedProduct);
            } else {
                System.out.println("Product with ID " + productID + " is not available. Please choose a valid product.");
            }
        }

        return selectedProducts;
    }

    // Helper method to calculate the total cost of the order
    private static double calculateTotalCost(List<Product> orderedProducts) {
        // Implement logic to calculate the total cost based on selected products
        // Return the total cost
        double total_cost = 0.0;
        for (Product product : orderedProducts) {
            total_cost += product.getPrice();
        }
        return total_cost;  // Placeholder, you need to implement this
    }

    // Example: Method to save data to file
    private static void saveDataToFile(InventoryManagementSystem ims, Scanner scanner) {
        System.out.println("Enter the file name to save data: ");
        String fileName = scanner.next();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(ims.getCustomers());
            oos.writeObject(ims.getInventory().getProductsInStock());
            System.out.println("Data saved successfully to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Method to load data from file
    private static void loadDataFromFile(InventoryManagementSystem ims, Scanner scanner) {
        System.out.println("Enter the file name to load data from: ");
        String fileName = scanner.next();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            List<Customer> loadedCustomers = (List<Customer>) ois.readObject();
            List<Product> loadedProducts = (List<Product>) ois.readObject();

            ims.getCustomers().addAll(loadedCustomers);
            ims.getInventory().setProductsInStock(loadedProducts);

            System.out.println("Data loaded successfully from " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    // Example: Method to display menu options
    private static void displayMenu() {
        System.out.println("\n==== Inventory Management System Menu ====");
        System.out.println("1. Register a new customer");
        System.out.println("2. Place an order");
        System.out.println("3. Display products in stock");
        System.out.println("4. Display all registered customers");
        System.out.println("5. Save data to file");
        System.out.println("6. Load data from file");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }
}
