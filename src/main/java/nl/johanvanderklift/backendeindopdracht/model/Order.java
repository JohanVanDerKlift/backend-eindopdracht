package nl.johanvanderklift.backendeindopdracht.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "client_order")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private double tax;
    private double deliveryCost;
    private double totalPrice;

    public Order() {
    }

    public Order(Long id, double tax, double deliveryCost, double totalPrice) {
        this.id = id;
        this.tax = tax;
        this.deliveryCost = deliveryCost;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
