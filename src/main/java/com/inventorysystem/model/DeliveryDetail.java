package com.inventorysystem.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DeliveryDetail")
public class DeliveryDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deliveryDetailId;

    @ManyToOne
    @JoinColumn(name = "deliveryId", nullable = false)
    private Delivery delivery;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    @Column(nullable = false)
    private int deliveryQuantity;

    @Temporal(TemporalType.DATE)
    private Date expectedDate;

    @Temporal(TemporalType.DATE)
    private Date actualDate;

    // Getters and setters...

    public int getDeliveryDetailId() {
        return deliveryDetailId;
    }

    public void setDeliveryDetailId(int deliveryDetailId) {
        this.deliveryDetailId = deliveryDetailId;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getDeliveryQuantity() {
        return deliveryQuantity;
    }

    public void setDeliveryQuantity(int deliveryQuantity) {
        this.deliveryQuantity = deliveryQuantity;
    }

    public Date getActualDate() {
        return actualDate;
    }

    public void setActualDate(Date actualDate) {
        this.actualDate = actualDate;
    }

    public Date getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(Date expectedDate) {
        this.expectedDate = expectedDate;
    }
}
