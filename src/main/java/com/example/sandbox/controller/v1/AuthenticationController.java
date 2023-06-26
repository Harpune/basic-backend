package com.example.sandbox.controller.v1;

import com.example.sandbox.model.dto.JwtAuthenticationResponse;
import com.example.sandbox.model.dto.SignUpRequest;
import com.example.sandbox.model.dto.SignInRequest;
import com.example.sandbox.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signUp(@Valid @RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@Valid @RequestBody SignInRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}
