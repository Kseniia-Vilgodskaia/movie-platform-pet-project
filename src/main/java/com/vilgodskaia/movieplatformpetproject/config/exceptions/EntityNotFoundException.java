package com.vilgodskaia.movieplatformpetproject.config.exceptions;

import com.vilgodskaia.movieplatformpetproject.model.EntityType;

import java.util.UUID;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(EntityType entityType, UUID id) {
        super(entityType.getName() + " not found by ID: " + id);
    }
}
