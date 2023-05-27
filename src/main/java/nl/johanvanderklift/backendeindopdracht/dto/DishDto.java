package nl.johanvanderklift.backendeindopdracht.dto;

import jakarta.validation.constraints.NotBlank;

public class DishDto {

    public Long id;
    @NotBlank
    public String name;
    @NotBlank
    public String category;
    public double price;
    public boolean available;
}
