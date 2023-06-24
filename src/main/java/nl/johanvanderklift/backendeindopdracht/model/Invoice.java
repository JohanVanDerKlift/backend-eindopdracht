package nl.johanvanderklift.backendeindopdracht.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private Double tax;
    @NotNull
    private Double deliveryCost;
    @NotNull
    private Double totalPrice;
    private Boolean isPaid;

    @OneToOne(mappedBy = "invoice")
    private Order order;


}
