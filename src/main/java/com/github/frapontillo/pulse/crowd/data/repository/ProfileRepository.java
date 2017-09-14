package com.github.frapontillo.pulse.crowd.data.repository;

import com.github.frapontillo.pulse.crowd.data.entity.Profile;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;

/**
 * {@link Repository} for {@link Profile}s.
 *
 * @author Francesco Pontillo
 */
public class ProfileRepository extends Repository<Profile, ObjectId> {

    public ProfileRepository() {
        super();
    }

    public ProfileRepository(String db) {
        super(db);
    }

    @Override public String getCollectionName() {
        return "Profile";
    }

    @Override public Class<Profile> getMappedClass() {
        return Profile.class;
    }

    /**
     * Get a {@link Profile} given the username.
     *
     * @param username The username to use while searching for the {@link Profile}.
     *
     * @return The found {@link Profile} or {@code null}.
     */
    public Profile getByUsername(String username) {
        Query<Profile> query = createQuery();
        query.field("username").equal(username);
        return query.get();
    }

}
