package nl.johanvanderklift.backendeindopdracht.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "dish")
public class Dish {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String category;
    private double price;
    private boolean available;
    @ManyToMany(mappedBy = "dishes")
    private List<Menu> menus;

    public Dish() {
    }

    public Dish(Long id, String name, String category, double price, boolean available) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String type) {
        this.category = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
