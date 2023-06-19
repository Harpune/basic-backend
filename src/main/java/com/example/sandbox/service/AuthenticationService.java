package com.example.sandbox.service;

import com.example.sandbox.dto.JwtAuthenticationResponse;
import com.example.sandbox.dto.SignUpRequest;
import com.example.sandbox.dto.SigninRequest;

public interface AuthenticationService {

    JwtAuthenticationResponse signup(SignUpRequest request);
    JwtAuthenticationResponse signin(SigninRequest request);
}
