package com.example.sandbox.controller;

import com.example.sandbox.model.Persona;
import com.example.sandbox.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/persona")
public class PersonaController {

    private final PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping(value = "/")
    public List<Persona> getPersonaList() {
        return this.personaService.findAllPersonas();
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertPersona(@RequestBody Persona persona) {
        this.personaService.save(persona);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Persona getPersonaById(@PathVariable("id") Long id) {
        return this.personaService.findPersonaById(id);
    }

    @PutMapping(value = "/{uid}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updatePersona(Persona persona) {

    }
}
