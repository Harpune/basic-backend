package com.example.sandbox.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PERSONA")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Email
    @Size(min = 5)
    @Column(name = "EMAIL")
    private String email;

    @NotNull
    @Size(min = 8)
    @Column(name = "PASSWORD")
    private String password;

    @Size(min = 2, max = 20)
    @Column(name = "FIRSTNAME", nullable = false, length = 20)
    private String firstname;

    @Size(min = 2, max = 20)
    @Column(name = "LASTNAME", nullable = false, length = 20)
    private String lastname;

    @Column(name = "DAY_OF_BIRTH", nullable = false)
    private LocalDate dayOfBirth;

}
