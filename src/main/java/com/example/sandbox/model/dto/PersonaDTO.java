package com.example.sandbox.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Relation(collectionRelation = "personas", itemRelation = "persona")
public class PersonaDTO {
    private final Long id;
    private final String email;
    private final String firstname;
    private final String lastname;
    private final LocalDate dayOfBirth;
}
