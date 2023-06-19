package com.example.sandbox.config;

import com.example.sandbox.model.Persona;
import com.example.sandbox.model.Role;
import com.example.sandbox.repository.PersonaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class PersonaLoader implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonaLoader.class);

    private final PersonaRepository personaRepository;

    private final List<Persona> personaList = Collections.singletonList(
            Persona.builder()
                    .email("lukas.hennig@gmx.de")
                    .password("Hallo123!")
                    .firstname("Lukas")
                    .lastname("Hennig")
                    .dayOfBirth(LocalDate.of(1994, 1, 25))
                    .role(Role.ADMIN)
                    .build());

    @Autowired
    public PersonaLoader(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public void run(ApplicationArguments args) {
        personaList.stream().map(personaRepository::save).forEach(persona -> LOGGER.info("Presaved: " + persona));
    }

}