package com.example.sandbox.service;

import com.example.sandbox.exception.ModelNotFoundException;
import com.example.sandbox.model.Persona;
import com.example.sandbox.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PersonaService implements ModelService<Persona, Long> {

    private final PersonaRepository personaRepository;

    @Autowired
    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public List<Persona> findAll() {
        return personaRepository.findAll();
    }

    @Override
    public Persona findOneByID(Long id) {
        return personaRepository.findById(id).orElseThrow(() -> new ModelNotFoundException(Persona.class, id));
    }

    @Override
    public List<Persona> findAllByID(List<Long> idList) {
        return personaRepository.findAllById(idList);
    }

    @Override
    public Persona saveOne(Persona persona) {
        return personaRepository.save(persona);
    }

    @Override
    public List<Persona> saveAll(List<Persona> modelList) {
        return personaRepository.saveAll(modelList);
    }

    @Override
    public Persona updateOneByID(Long id, Persona newPersona) {
        return personaRepository.findById(id).map(persona -> {
            persona.setFirstname(newPersona.getFirstname());
            persona.setLastname(newPersona.getLastname());
            persona.setDayOfBirth(newPersona.getDayOfBirth());
            persona.setEmail(newPersona.getEmail());
            return personaRepository.save(persona);
        }).orElseGet(() -> personaRepository.save(newPersona));
    }

    @Override
    public List<Persona> updateAll(Map<Long, Persona> modelMap) {
        return modelMap.entrySet().stream()
                .map(entry -> updateOneByID(entry.getKey(), entry.getValue()))
                .toList();
    }

    @Override
    public void deleteOneByID(Long id) {
        personaRepository.deleteById(id);
    }
}
