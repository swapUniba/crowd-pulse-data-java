package com.github.frapontillo.pulse.crowd.data.repository;

import com.github.frapontillo.pulse.crowd.data.entity.RefreshToken;
import org.bson.types.ObjectId;

/**
 * {@link Repository} for {@link RefreshToken}s.
 *
 * @author Francesco Pontillo
 */
public class RefreshTokenRepository extends Repository<RefreshToken, ObjectId> {
    @Override public String getCollectionName() {
        return "RefreshToken";
    }

    @Override public Class<RefreshToken> getMappedClass() {
        return RefreshToken.class;
    }
}
