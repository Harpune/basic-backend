package com.example.sandbox.exception;

import org.springframework.lang.NonNull;

public class ModelNotFoundException extends RuntimeException {
    public ModelNotFoundException(Long id) {
        super("Could not find model with id %d".formatted(id));
    }
    public ModelNotFoundException(@NonNull Class<?> clazz, @NonNull Long id) {
        super("Could not find %s with id %d".formatted(clazz.getSimpleName(), id));
    }
}
