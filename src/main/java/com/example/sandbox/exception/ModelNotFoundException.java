package com.example.sandbox.exception;

public class ModelNotFoundException extends RuntimeException {
    public ModelNotFoundException(Long id) {
        super("Could not find model with id %d".formatted(id));
    }
    public ModelNotFoundException(Class clazz, Long id) {
        super("Could not find %s with id %d".formatted(clazz.getSimpleName(), id));
    }
}
