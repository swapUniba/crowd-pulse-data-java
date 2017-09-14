package com.github.frapontillo.pulse.crowd.data.repository;

import com.github.frapontillo.pulse.crowd.data.entity.App;
import org.bson.types.ObjectId;

/**
 * {@link Repository} for {@link App}s.
 *
 * @author Francesco Pontillo
 */
public class AppRepository extends Repository<App, ObjectId> {
    @Override public String getCollectionName() {
        return "App";
    }

    @Override public Class<App> getMappedClass() {
        return App.class;
    }
}
