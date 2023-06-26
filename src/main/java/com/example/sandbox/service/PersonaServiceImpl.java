package com.example.sandbox.service;

import com.example.sandbox.model.exception.ModelNotFoundException;
import com.example.sandbox.model.Persona;
import com.example.sandbox.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;

    @Override
    public List<Persona> findAll() {
        return personaRepository.findAll();
    }

    @Override
    public Persona findOneByID(Long id) {
        if (id == null) {
            throw new RuntimeException("ID must not be null");
        }
        return personaRepository.findById(id).orElseThrow(() -> new ModelNotFoundException(Persona.class, String.valueOf(id)));
    }

    @Override
    public List<Persona> findAllByID(List<Long> idList) {
        idList.removeIf(Objects::isNull);
        return personaRepository.findAllById(idList);
    }

    @Override
    public boolean existsById(Long id) {
        if (id == null) {
            throw new RuntimeException("ID must not be null");
        }
        return personaRepository.existsById(id);
    }

    @Override
    public Persona findOneByEmail(String email) {
        if (email == null) {
            throw new RuntimeException("Email must not be null");
        }
        return personaRepository.findByEmail(email).orElseThrow(() -> new ModelNotFoundException(Persona.class, email));
    }

    @Override
    public boolean existsByEmail(String email) {
        if (email == null) {
            throw new RuntimeException("Email must not be null");
        }
        return personaRepository.existsByEmail(email);
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
            persona.setEmail(newPersona.getEmail());
            persona.setPassword(newPersona.getPassword());
            persona.setFirstname(newPersona.getFirstname());
            persona.setLastname(newPersona.getLastname());
            persona.setDayOfBirth(newPersona.getDayOfBirth());
            return personaRepository.save(persona);
        }).orElseGet(() -> personaRepository.save(newPersona));
    }

    @Override
    public Persona updateOneByEmail(String email, Persona newPersona) {
        return personaRepository.findByEmail(email).map(persona -> {
            persona.setEmail(newPersona.getEmail());
            persona.setPassword(newPersona.getPassword());
            persona.setFirstname(newPersona.getFirstname());
            persona.setLastname(newPersona.getLastname());
            persona.setDayOfBirth(newPersona.getDayOfBirth());
            return personaRepository.save(persona);
        }).orElseGet(() -> personaRepository.save(newPersona));
    }

    @Override
    public List<Persona> updateAll(Map<Long, Persona> personaMap) {
        return personaMap.entrySet().stream()
                .map(entry -> updateOneByID(entry.getKey(), entry.getValue()))
                .toList();
    }

    @Override
    public void deleteOneByID(Long id) {
        personaRepository.deleteById(id);
    }

    @Override
    public void deleteAllByID(List<Long> idList) {
        personaRepository.deleteAllById(idList);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return email -> personaRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Persona with %s not found".formatted(email)));
    }
}
