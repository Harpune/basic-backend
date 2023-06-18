package com.example.sandbox.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Verification {
    private boolean verified;
    private LocalDateTime localDateTime;
}
