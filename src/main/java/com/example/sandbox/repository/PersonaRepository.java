package com.example.sandbox.repository;

import com.example.sandbox.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    Optional<Persona> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsById(@NonNull Long id);
}
