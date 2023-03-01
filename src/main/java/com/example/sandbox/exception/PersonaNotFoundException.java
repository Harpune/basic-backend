package com.example.sandbox.exception;

public class PersonaNotFoundException extends RuntimeException {
    public PersonaNotFoundException(Long id) {
        super("Could not find persona with id %d".formatted(id));
    }
}
