package OOPS_Practice.Inventory_Management_System;

import java.io.Serializable;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {
    private String customerID;
    private String customerName;
    private String email;
    private List<Order> orderHistory;

    // Constructor, methods to place orders, view order history, etc.


    public Customer(String customerID, String customerName, String email, List<Order> orderHistory) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.email = email;
        this.orderHistory = orderHistory;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(List<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }


    // Method to place a new order
    public void placeOrder(Order order) {
        // Add the order to the order history
        orderHistory.add(order);
        // Perform any additional actions related to placing an order
        System.out.println("Total bill for the order placed: ₹"+order.getTotalCost()+"/-");
        order.updateOrderStatus("Order Placed");

    }

    // Method to view details of a specific order in the order history
    public void viewOrderDetails(String orderID) {
        // Find the order with the specified orderID in the order history
        // Display details of the order
        for (Order order: orderHistory) {
            if(orderID.equals(order.getOrderID())){
                System.out.println("Total cost of order with OrderID " + orderID + " is => " + order.getTotalCost());
                System.out.println("Order status for OrderID " + orderID + " is => " + order.getOrderStatus());
                return;
            }
        }
        System.out.println("No order with OderID: " + orderID + " is available ");
    }

    // Method to view the entire order history
    public void viewOrderHistory() {
        // Display details of all orders in the order history
        for (Order order : orderHistory) {
            System.out.println("OrderID: " + order.getOrderID());
            System.out.println("Total cost: " + order.getTotalCost());
            System.out.println("Order status: " + order.getOrderStatus());
            System.out.println("--------------");
        }

    }

    // Method to calculate the total amount spent by the customer
    public double calculateTotalAmountSpent() {
        // Iterate through the order history and sum up the total cost of each order
        double total_amount_spent_by_customer = 0.0;
        for (Order order: orderHistory) {
            total_amount_spent_by_customer+=order.getTotalCost();
        }
        // Return the total amount spent
        return total_amount_spent_by_customer;
    }

    // Additional methods for other functionality...

    // Getter method for the total number of orders placed
    public int getTotalOrdersPlaced() {
        // Return the size of the order history list
        return orderHistory.size();

    }
}
