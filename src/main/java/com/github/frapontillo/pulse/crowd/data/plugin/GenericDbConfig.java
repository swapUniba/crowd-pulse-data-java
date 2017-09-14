package com.github.frapontillo.pulse.crowd.data.plugin;

import com.github.frapontillo.pulse.spi.IPluginConfig;

/**
 * Generic config class that handles the database name.
 *
 * @author Francesco Pontillo
 */
public abstract class GenericDbConfig<T> implements IPluginConfig<T> {
    private String db;

    /**
     * Get the database name to access.
     *
     * @return The database name.
     */
    public String getDb() {
        return db;
    }

    /**
     * Set the database name to access according to this configuration.
     *
     * @param db The database name.
     */
    public void setDb(String db) {
        this.db = db;
    }
}
