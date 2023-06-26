package com.example.sandbox.config;

import com.example.sandbox.model.Persona;
import com.example.sandbox.model.Role;
import com.example.sandbox.service.PersonaService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonaLoader implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonaLoader.class);

    private final PersonaService personaService;

    private final List<Persona> personaList = Collections.singletonList(
            Persona.builder()
                    .email("lukas.hennig@gmx.de")
                    .password("$2a$10$Evi6UHi4S2eMWYSbxGUL0e71Wrz8ZLRREdjYkC3XT.C31P/tM7aVK")
                    .firstname("Lukas")
                    .lastname("Hennig")
                    .dayOfBirth(LocalDate.of(1994, 1, 25))
                    .role(Role.ADMIN)
                    .build());

    public void run(ApplicationArguments args) {
        personaList.stream().map(persona -> personaService.updateOneByEmail(persona.getEmail(), persona)).forEach(persona -> LOGGER.info("Presaved: " + persona));
    }

}
