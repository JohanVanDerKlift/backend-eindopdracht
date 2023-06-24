package nl.johanvanderklift.backendeindopdracht.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "menus")
public class Menu {
    @Id
    @GeneratedValue
    private Long id;
    private String type;
    @ManyToMany
    @JsonIgnore
    private List<Dish> dishes;

}
