package com.github.frapontillo.pulse.crowd.data.entity;

import org.bson.types.ObjectId;

/**
 * Specific implementation of {@link GenericEntity} where {@code id}s are {@link ObjectId}s.
 *
 * @author Francesco Pontillo
 */
public class Entity extends GenericEntity<ObjectId> {
    /**
     * Constructor of the Entity, sets an newly created {@link #id}.
     */
    public Entity() {
        this.setId(new ObjectId());
    }
}
