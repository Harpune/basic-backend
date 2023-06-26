package com.example.sandbox.config;

import com.example.sandbox.model.dto.ApiError;
import com.example.sandbox.model.exception.ModelNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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

        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND)
                .message(ex.getLocalizedMessage())
                .errors(Collections.singletonList(ex.toString()))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        // Collect all errors
        List<String> fieldErrors = ex.getBindingResult().getFieldErrors().stream().map(error -> error.getField() + ": " + error.getDefaultMessage()).toList();
        List<String> objectErrors = ex.getBindingResult().getGlobalErrors().stream().map(error -> error.getObjectName() + ": " + error.getDefaultMessage()).toList();

        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST)
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ex.getLocalizedMessage())
                .errors(Stream.concat(fieldErrors.stream(), objectErrors.stream()).toList())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> processRuntimeException(RuntimeException e) {

        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("Server sagt: Nein")
                .errors(Collections.singletonList(e.toString()))
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({ InternalAuthenticationServiceException.class})
    public ResponseEntity<Object> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException e) {

        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.UNAUTHORIZED.value())
                .status(HttpStatus.UNAUTHORIZED)
                .message(e.getLocalizedMessage())
                .errors(Collections.singletonList(e.toString()))
                .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({ AuthenticationException.class })
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException e) {

        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.UNAUTHORIZED.value())
                .status(HttpStatus.UNAUTHORIZED)
                .message(e.getLocalizedMessage())
                .errors(Collections.singletonList(e.toString()))
                .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
    }

}
