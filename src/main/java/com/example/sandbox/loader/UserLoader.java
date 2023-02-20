package com.example.sandbox.loader;

import com.example.sandbox.model.Persona;
import com.example.sandbox.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserLoader implements ApplicationRunner {

    private final PersonaRepository personaRepository;

    @Autowired
    public UserLoader(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public void run(ApplicationArguments args) {
        personaRepository.save(new Persona(1L, "Lukas", "Hennig",  LocalDate.of(1994, 1,25),"lukas.hennig@adesso.de"));
        personaRepository.save(new Persona(2L, "Alice", "Seim",  LocalDate.of(1994, 12,9),"alicebseim@gmail.de"));
    }

}
