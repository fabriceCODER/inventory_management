package com.inventorysystem.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @Column(nullable = false, length = 100)
    private String customerName;

    @Column(nullable = false, length = 200)
    private String customerAddress;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Delivery> deliveries;

    public Customer(int customerId, String customerAddress, Set<Delivery> deliveries, String customerName) {
        this.customerId = customerId;
        this.customerAddress = customerAddress;
        this.deliveries = deliveries;
        this.customerName = customerName;
    }

    // Getters and setters...


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Set<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(Set<Delivery> deliveries) {
        this.deliveries = deliveries;
    }
}
