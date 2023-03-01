package com.example.sandbox.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonaDTO {
    Long id;
    String firstname;
    String lastname;
    LocalDate dayOfBirth;
    String email;
}
