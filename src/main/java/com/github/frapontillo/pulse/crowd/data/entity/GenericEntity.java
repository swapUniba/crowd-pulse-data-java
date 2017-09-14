package com.github.frapontillo.pulse.crowd.data.entity;

import org.mongodb.morphia.annotations.Id;

/**
 * Generic entities that will be stored in a Mongo database.
 * Every {@link GenericEntity} will have at least a {@code id} of some kind.
 *
 * @author Francesco Pontillo
 */
public class GenericEntity<T> {
    @Id private T id;

    /**
     * Get the ID of the GenericEntity.
     *
     * @return The ID of the object.
     */
    public T getId() {
        return id;
    }

    /**
     * Set the ID of the GenericEntity.
     *
     * @param id The ID of the object.
     */
    public void setId(T id) {
        this.id = id;
    }
}
