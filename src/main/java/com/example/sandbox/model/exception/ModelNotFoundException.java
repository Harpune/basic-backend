package com.example.sandbox.model.exception;

import org.springframework.lang.NonNull;

public class ModelNotFoundException extends RuntimeException {
    public ModelNotFoundException(@NonNull Class<?> clazz, @NonNull String id) {
        super("Could not find %s with id='%s'".formatted(clazz.getSimpleName(), id));
    }
}
