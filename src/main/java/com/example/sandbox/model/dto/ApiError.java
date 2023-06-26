package com.example.sandbox.model.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ApiError(LocalDateTime timestamp, HttpStatus status, int code, String message, List<String> errors) {}