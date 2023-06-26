package com.example.sandbox.service;

import com.example.sandbox.model.dto.JwtAuthenticationResponse;
import com.example.sandbox.model.dto.SignUpRequest;
import com.example.sandbox.model.dto.SignInRequest;

public interface AuthenticationService {

    JwtAuthenticationResponse signup(SignUpRequest request);
    JwtAuthenticationResponse signin(SignInRequest request);
}
