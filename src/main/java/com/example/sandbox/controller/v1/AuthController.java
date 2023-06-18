package com.example.sandbox.controller.v1;

import com.example.sandbox.dto.AuthDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/")
    public String authenticateAndGetToken(@RequestBody AuthDTO authDTO) {
        return "Hello";
    }
}
