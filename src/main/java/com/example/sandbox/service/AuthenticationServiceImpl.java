package com.example.sandbox.service;

import com.example.sandbox.dto.JwtAuthenticationResponse;
import com.example.sandbox.dto.SignUpRequest;
import com.example.sandbox.dto.SigninRequest;
import com.example.sandbox.model.Persona;
import com.example.sandbox.model.Role;
import com.example.sandbox.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final PersonaRepository personaRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        Persona persona = Persona.builder()
                .firstname(request.getFirstName())
                .lastname(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        personaRepository.save(persona);

        String jwt = jwtService.generateToken(persona);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        Persona persona = personaRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        String jwt = jwtService.generateToken(persona);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
