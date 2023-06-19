package com.example.sandbox.unit;

import com.example.sandbox.fixture.PersonaFixture;
import com.example.sandbox.model.Persona;
import com.example.sandbox.service.PersonaServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PersonaServiceTest {

    @Autowired
    private PersonaServiceImpl personaService;

    @Test
    public void should_create_entry() {
        // given
        Persona one = PersonaFixture.create();

        // when
        Persona saved = personaService.saveOne(one);

        // then, given
        assertTrue(personaService.exists(saved.getId()));

        // when
        personaService.deleteOneByID(saved.getId());

        // then
        assertFalse(personaService.exists(saved.getId()));
    }
}