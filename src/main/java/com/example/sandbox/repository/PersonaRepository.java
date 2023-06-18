package com.example.sandbox.repository;

import com.example.sandbox.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    Persona findPersonaByEmail(String email);
    boolean existsByEmail(String email);
}
