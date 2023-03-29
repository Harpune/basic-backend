package com.example.sandbox.controller;

import com.example.sandbox.dto.PersonaDTO;
import com.example.sandbox.mapper.PersonaMapper;
import com.example.sandbox.model.Persona;
import com.example.sandbox.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/personas")
public class PersonaController {

    private final PersonaService personaService;
    private final PersonaMapper personaMapper;

    @Autowired
    public PersonaController(PersonaService personaService, PersonaMapper personaMapper) {
        this.personaService = personaService;
        this.personaMapper = personaMapper;
    }

    @GetMapping(value = "")
    public CollectionModel<EntityModel<PersonaDTO>> all() {
        List<EntityModel<PersonaDTO>> personas = personaService.findAll().stream()
                .map(persona -> EntityModel.of(personaMapper.toDTO(persona),
                        linkTo(methodOn(PersonaController.class).oneById(persona.getId())).withSelfRel(),
                        linkTo(methodOn(PersonaController.class).all()).withRel("all")))
                .toList();

        return CollectionModel.of(personas, linkTo(methodOn(PersonaController.class).all()).withSelfRel());
    }

    @GetMapping(value = "[{idArray}]")
    public CollectionModel<EntityModel<PersonaDTO>> allById(@PathVariable Long[] idArray) {
        List<EntityModel<PersonaDTO>> personas = personaService.findAllByID(List.of(idArray)).stream()
                .map(persona -> EntityModel.of(personaMapper.toDTO(persona),
                        linkTo(methodOn(PersonaController.class).oneById(persona.getId())).withSelfRel(),
                        linkTo(methodOn(PersonaController.class).all()).withRel("all")))
                .toList();
        return CollectionModel.of(personas, linkTo(methodOn(PersonaController.class).all()).withSelfRel());
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<PersonaDTO> newPersona(@RequestBody PersonaDTO newPersona) {

        Persona persona = personaService.saveOne(personaMapper.toEntity(newPersona));
        return EntityModel.of(personaMapper.toDTO(persona),
                linkTo(methodOn(PersonaController.class).oneById(persona.getId())).withSelfRel(),
                linkTo(methodOn(PersonaController.class).all()).withRel("all"));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<PersonaDTO> oneById(@PathVariable("id") Long id) {
        Persona persona = personaService.findOneByID(id);
        return EntityModel.of(personaMapper.toDTO(persona),
                linkTo(methodOn(PersonaController.class).oneById(persona.getId())).withSelfRel(),
                linkTo(methodOn(PersonaController.class).all()).withRel("all"));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<PersonaDTO> existingPersona(@PathVariable("id") Long id, @RequestBody PersonaDTO newPersona) {
        Persona persona = personaService.updateOneByID(id, personaMapper.toEntity(newPersona));
        return EntityModel.of(personaMapper.toDTO(persona),
                linkTo(methodOn(PersonaController.class).oneById(persona.getId())).withSelfRel(),
                linkTo(methodOn(PersonaController.class).all()).withRel("all"));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deletePersona(@PathVariable("id") Long id) {
        personaService.deleteOneByID(id);
    }
}
