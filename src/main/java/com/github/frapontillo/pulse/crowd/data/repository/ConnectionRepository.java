package com.github.frapontillo.pulse.crowd.data.repository;

import com.github.frapontillo.pulse.crowd.data.entity.Connection;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import rx.Observable;


/**
 * {@link Repository} for {@link Connection}.
 *
 * @author Fabio De Pasquale
 */
public class ConnectionRepository extends Repository<Connection, ObjectId> {

    public ConnectionRepository(String db) {
        super(db);
    }

    @Override
    public String getCollectionName() {
        return "Connection";
    }

    @Override
    public Class<Connection> getMappedClass() {
        return Connection.class;
    }

    @Override
    public Query<Connection> findBetweenKeys(ObjectId from, ObjectId to) {
        return super.findBetweenKeys(from, to).order("date");
    }

    public Observable<Connection> find(String displayName, String source) {
        Query<Connection> query = createQuery();
        if (displayName != null ) {
            query.field("displayName").equal(displayName);
        }
        if (source != null) {
            query.field("source").equal(source);
        }
        query.order("displayName");
        return Observable.from(query.fetch());
    }

    public Connection updateOrInsert(Connection Connection) {
        Query<Connection> queryOriginalId = createQuery().field("_id").equal(Connection.getId());

        if(queryOriginalId.countAll() > 0)
            getDs().delete(queryOriginalId);

        getDs().save(Connection);
        return Connection;
    }
}
