package nl.johanvanderklift.backendeindopdracht.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "order_lines")
public class OrderLine {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private int amount;
    @Column(nullable = false)
    private String dishName;
    @Column(nullable = false)
    private double price;
    private double totalPrice;

    @ManyToOne
    private Order order;
}
