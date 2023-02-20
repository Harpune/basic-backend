package com.example.sandbox.service;

import com.example.sandbox.model.Persona;
import com.example.sandbox.repository.PersonaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;

    @Autowired
    public PersonaService(PersonaRepository personaRepository){
        this.personaRepository = personaRepository;
    }

    public List<Persona> findAllPersonas() {
        return personaRepository.findAll();
    }

    public Persona findPersonaById(Long id) {
        return this.personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Persona with id ${id} not found"));
    }

    public void save(Persona persona) {
        personaRepository.save(persona);
    }

}
