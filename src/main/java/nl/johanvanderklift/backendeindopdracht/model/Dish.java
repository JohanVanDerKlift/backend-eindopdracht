package nl.johanvanderklift.backendeindopdracht.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "dish")
public class Dish {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String category;
    @NotNull
    private Double price;
    @NotNull
    private Boolean available;
    @ManyToMany(mappedBy = "dishes")
    private List<Menu> menus;

}