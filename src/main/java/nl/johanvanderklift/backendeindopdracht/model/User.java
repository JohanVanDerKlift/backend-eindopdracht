package nl.johanvanderklift.backendeindopdracht.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @Column(nullable = false, unique = true)
    @Email
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String companyName;
    @Column(nullable = false)
    private String adres;
    @Column(nullable = false)
    private String zipCode;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private boolean hasCredit;

}