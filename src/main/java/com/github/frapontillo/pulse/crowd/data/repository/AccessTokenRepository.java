package com.github.frapontillo.pulse.crowd.data.repository;

import com.github.frapontillo.pulse.crowd.data.entity.AccessToken;
import org.bson.types.ObjectId;

/**
 * {@link Repository} for {@link AccessToken}s.
 *
 * @author Francesco Pontillo
 */
public class AccessTokenRepository extends Repository<AccessToken, ObjectId> {
    @Override public String getCollectionName() {
        return "AccessToken";
    }

    @Override public Class<AccessToken> getMappedClass() {
        return AccessToken.class;
    }
}
