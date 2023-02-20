package com.example.sandbox.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PERSONA")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotNull
    private Long id;
    @NotNull
    @Size(min = 2, max = 20)
    @Column(name = "FIRSTNAME", nullable = false, length = 20)
    private String firstname;
    @NotNull
    @Size(min = 2, max = 20)
    @Column(name = "LASTNAME", nullable = false, length = 20)
    private String lastname;
    @NotNull
    @Column(name = "DAY_OF_BIRTH", nullable = false)
    private LocalDate dayOfBirth;
    @NotNull
    @Email
    @Size(min = 5, max = 50)
    @Column(name = "EMAIL", nullable = false)
    private String email;
}
