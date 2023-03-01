package com.example.sandbox.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ApiError(LocalDateTime timestamp, HttpStatus status, int code, String message, List<String> errors) {}