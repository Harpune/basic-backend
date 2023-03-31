package com.example.sandbox.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonaDTO {
    private final Long id;
    private final String firstname;
    private final String lastname;
    private final LocalDate dayOfBirth;
    private final String email;
}
