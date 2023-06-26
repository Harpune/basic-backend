package com.example.sandbox.service;

import com.example.sandbox.model.Persona;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface PersonaService extends ModelService<Persona, Long> {
    UserDetailsService userDetailsService();

    Persona updateOneByEmail(String email, Persona persona);

    Persona findOneByEmail(String email);

    boolean existsByEmail(String email);
}
