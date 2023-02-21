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
    public List<Persona> getAllPersona() {
        return personaService.findAll();
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Persona postPersona(@RequestBody Persona persona) {
        return personaService.save(persona);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Persona getPersonaById(@PathVariable("id") Long id) {
        return personaService.findOneById(id);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Persona putPersona(@PathVariable("id") Long id, @RequestBody Persona persona) {
        return personaService.replace(id, persona);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deletePersona(@PathVariable("id") Long id) {
        personaService.delete(id);
    }
}
