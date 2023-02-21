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

    public List<Persona> findAll() {
        return personaRepository.findAll();
    }

    public Persona findOneById(Long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Persona with id ${id} not found"));
    }

    public Persona save(Persona persona) {
        return personaRepository.save(persona);
    }

    public Persona replace(Long id, Persona newPersona) {
        return personaRepository.findById(id)
                .map(persona -> {
                    persona.setFirstname(newPersona.getFirstname());
                    persona.setLastname(newPersona.getLastname());
                    persona.setDayOfBirth(newPersona.getDayOfBirth());
                    persona.setEmail(newPersona.getEmail());
                    return personaRepository.save(persona);
                })
                .orElseGet(() -> personaRepository.save(newPersona));
    }

    public void delete(Long id) {
        personaRepository.deleteById(id);
    }
}
