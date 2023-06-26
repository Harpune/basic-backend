package com.example.sandbox.service;

import com.example.sandbox.model.dto.JwtAuthenticationResponse;
import com.example.sandbox.model.dto.SignUpRequest;
import com.example.sandbox.model.dto.SignInRequest;
import com.example.sandbox.model.Persona;
import com.example.sandbox.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PersonaService personaService;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        // Check if user already exists
        if (personaService.existsByEmail(request.getEmail())) {
            throw new AuthenticationServiceException("Credentials already in use.");
        }

        // Build new Persona
        Persona persona = Persona.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        // Save new Persona
        Persona savedPersona = personaService.saveOne(persona);

        // Create Token
        String jwt = jwtService.generateToken(savedPersona);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        Persona persona = personaService.findOneByEmail(request.getEmail());
        String jwt = jwtService.generateToken(persona);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
