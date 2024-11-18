package com.inventorysystem.model;



import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "OrderDetail")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderDetailId;

    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    @Column(nullable = false)
    private int orderQuantity;

    @Temporal(TemporalType.DATE)
    private Date expectedDate;

    @Temporal(TemporalType.DATE)
    private Date actualDate;

    // Getters and setters...
}

