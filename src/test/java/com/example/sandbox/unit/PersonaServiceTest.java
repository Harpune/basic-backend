package com.example.sandbox.unit;

import com.example.sandbox.fixture.PersonaFixture;
import com.example.sandbox.model.Persona;
import com.example.sandbox.service.PersonaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PersonaServiceTest {

    @Autowired
    private PersonaService personaService;

    @Test
    @Transactional
    public void should_create_entry() {
        // given
        Persona one = PersonaFixture.create();

        // when
        Persona saved = personaService.saveOne(one);

        // then, given
        assertTrue(personaService.existsById(saved.getId()));
    }
}