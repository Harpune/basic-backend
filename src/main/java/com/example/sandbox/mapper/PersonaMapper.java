package com.example.sandbox.mapper;

import com.example.sandbox.model.dto.PersonaDTO;
import com.example.sandbox.model.Persona;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonaMapper {
    PersonaDTO toDTO(Persona persona);
    Persona toEntity(PersonaDTO personaDTO);
}
