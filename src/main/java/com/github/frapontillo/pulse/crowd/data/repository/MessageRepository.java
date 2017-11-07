package com.github.frapontillo.pulse.crowd.data.repository;

import com.github.frapontillo.pulse.crowd.data.entity.Message;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import rx.Observable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Aggregates.sort;
import static com.mongodb.client.model.Filters.*;

/**
 * {@link Repository} for {@link Message}s.
 *
 * @author Francesco Pontillo
 */
public class MessageRepository extends Repository<Message, ObjectId> {

    public MessageRepository(String db) {
        super(db);
    }

    @Override public String getCollectionName() {
        return "Message";
    }

    @Override public Class<Message> getMappedClass() {
        return Message.class;
    }

    @Override public Query<Message> findBetweenKeys(ObjectId from, ObjectId to) {
        return super.findBetweenKeys(from, to).order("date");
    }

    public Observable<Message> find(Date since, Date until, List<String> languages) {
        Query<Message> query = createQuery();
        if (since != null) {
            query.field("date").greaterThanOrEq(since);
        }
        if (until != null) {
            query.field("date").lessThanOrEq(until);
        }
        if (languages != null && languages.size() > 0) {
            query.field("language").in(languages);
        }
        query.order("date");
        return Observable.from(query.fetch());
    }

    public Observable<Message> findRx(Date since, Date until, List<String> languages) {
        List<Bson> params = new ArrayList<>();
        if (since != null) {
            params.add(gte("date", since));
        }
        if (until != null) {
            params.add(lte("date", until));
        }
        if (languages != null) {
            params.add(in("language", languages));
        }
        Bson filter = null;
        if (params.size() > 0) {
            filter = match(and(params));
        }
        Bson order = sort(new BsonDocument("date", new BsonInt32(1)));
        return this.aggregateRx(filter, order);
    }

    public Message updateOrInsert(Message message) {
        Query<Message> queryOriginalId = createQuery().field("oId").equal(message.getoId());
        
        if(queryOriginalId.countAll() > 0)
        	getDs().delete(queryOriginalId);
        
        getDs().save(message);
        return message;
    }
}
