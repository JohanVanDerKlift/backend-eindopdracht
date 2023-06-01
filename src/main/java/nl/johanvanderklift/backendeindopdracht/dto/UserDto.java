package nl.johanvanderklift.backendeindopdracht.dto;

import jakarta.validation.constraints.NotBlank;

public class UserDto {
    public Long id;
    @NotBlank
    public String email;
    @NotBlank
    public String password;
    public String firstName;
    public String lastName;
    public String companyName;
    public String adres;
    public String zipCode;
    public String phoneNumber;
}
