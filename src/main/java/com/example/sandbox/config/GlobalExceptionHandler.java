package com.example.sandbox.config;

import com.example.sandbox.dto.ApiError;
import com.example.sandbox.model.exception.ModelNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<Object> handlePersonaNotFoundException(ModelNotFoundException ex) {
        ApiError apiError = new ApiError(LocalDateTime.now(), HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), ex.getLocalizedMessage(), Collections.singletonList(ex.toString()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        // Collect all errors
        List<String> fieldErrors = ex.getBindingResult().getFieldErrors().stream().map(error -> error.getField() + ": " + error.getDefaultMessage()).toList();
        List<String> objectErrors = ex.getBindingResult().getGlobalErrors().stream().map(error -> error.getObjectName() + ": " + error.getDefaultMessage()).toList();

        ApiError apiError = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage(), Stream.concat(fieldErrors.stream(), objectErrors.stream()).toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

}
