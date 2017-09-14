package com.github.frapontillo.pulse.crowd.data.repository;

import com.github.frapontillo.pulse.crowd.data.entity.User;
import org.bson.types.ObjectId;

/**
 * {@link Repository} for {@link User}s.
 *
 * @author Francesco Pontillo
 */
public class UserRepository extends Repository<User, ObjectId> {
    @Override public String getCollectionName() {
        return "User";
    }

    @Override public Class<User> getMappedClass() {
        return User.class;
    }
}
