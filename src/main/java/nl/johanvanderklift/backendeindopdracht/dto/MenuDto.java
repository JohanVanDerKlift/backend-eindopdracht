package nl.johanvanderklift.backendeindopdracht.dto;

import jakarta.validation.constraints.NotBlank;

public class MenuDto {
    public Long id;
    @NotBlank
    public String type;
}
