package com.example.sandbox.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Data
public class SignUpRequest {
    @Email(message = "The email address is invalid.", flags = { Pattern.Flag.CASE_INSENSITIVE })
    @NotEmpty(message = "The email address is required.")
    private String email;

    @Size(min = 8, message = "The password is too short.")
    @NotEmpty(message = "The password is required.")
    private String password;

    private String firstname;

    private String lastname;
}