package com.github.frapontillo.pulse.crowd.data.repository;

import com.github.frapontillo.pulse.crowd.data.entity.PersonalData;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import rx.Observable;

import java.util.Date;


/**
 * {@link Repository} for {@link PersonalData}.
 *
 * @author Cosimo Lovascio
 */
public class PersonalDataRepository extends Repository<PersonalData, ObjectId> {

    public PersonalDataRepository(String db) {
        super(db);
    }

    @Override
    public String getCollectionName() {
        return "PersonalData";
    }

    @Override
    public Class<PersonalData> getMappedClass() {
        return PersonalData.class;
    }

    @Override
    public Query<PersonalData> findBetweenKeys(ObjectId from, ObjectId to) {
        return super.findBetweenKeys(from, to).order("date");
    }

    public Observable<PersonalData> find(Date since, Date until, String source) {
        Query<PersonalData> query = createQuery();
        if (since != null) {
            query.field("timestamp").greaterThanOrEq(since);
        }
        if (until != null) {
            query.field("timestamp").lessThanOrEq(until);
        }
        if (source != null ) {
            query.field("source").equal(source);
        }
        query.order("timestamp");
        return Observable.from(query.fetch());
    }

    public PersonalData updateOrInsert(PersonalData personalData) {
        Query<PersonalData> queryOriginalId = createQuery().field("oId").equal(personalData.getoId());

        if(queryOriginalId.countAll() > 0)
            getDs().delete(queryOriginalId);

        getDs().save(personalData);
        return personalData;
    }
}
