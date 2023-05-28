package nl.johanvanderklift.backendeindopdracht.dto;

import jakarta.validation.constraints.NotBlank;

public class MenuDto {
    @NotBlank
    public Long id;
    @NotBlank
    public String type;
}
