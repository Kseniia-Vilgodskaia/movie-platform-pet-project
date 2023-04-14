package com.vilgodskaia.movieplatform.core.config.exceptions;

import com.vilgodskaia.movieplatform.core.model.EntityType;

import java.util.UUID;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(EntityType entityType, UUID id) {
        super(entityType.getName() + " not found by ID: " + id);
    }
}
