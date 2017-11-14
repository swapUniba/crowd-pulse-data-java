package com.github.frapontillo.pulse.crowd.data.plugin;

import com.github.frapontillo.pulse.crowd.data.entity.PersonalData;
import com.github.frapontillo.pulse.crowd.data.repository.PersonalDataRepository;
import com.github.frapontillo.pulse.rx.PulseSubscriber;
import com.github.frapontillo.pulse.spi.IPlugin;
import com.github.frapontillo.pulse.spi.PluginConfigHelper;
import com.google.gson.JsonElement;
import org.bson.types.ObjectId;
import rx.Observable;

public class PersonalDataPersister extends IPlugin<PersonalData, PersonalData, PersonalDataPersister.PersonalDataPersisterOptions> {

    public final static String PLUGIN_NAME = "personaldata-persist";
    private PersonalDataRepository personalDataRepository;


    @Override
    public String getName() {
        return PLUGIN_NAME;
    }

    @Override
    public PersonalDataPersisterOptions getNewParameter() {
        return new PersonalDataPersisterOptions();
    }

    @Override
    protected Observable.Operator<PersonalData, PersonalData> getOperator(
            PersonalDataPersisterOptions parameters) {

        // init the personal data repository with the given target DB, if any
        personalDataRepository = new PersonalDataRepository(parameters.getDb());

        return subscriber -> new PulseSubscriber<PersonalData>(subscriber) {
            @Override public void onNext(PersonalData personalData) {
                ObjectId id = personalData.getId();
                reportElementAsStarted(id);

                personalDataRepository.updateOrInsert(personalData);

                reportElementAsEnded(id);
                subscriber.onNext(personalData);
            }

            @Override public void onCompleted() {
                reportPluginAsCompleted();
                super.onCompleted();
            }

            @Override public void onError(Throwable e) {
                reportPluginAsErrored();
                super.onError(e);
            }
        };
    }


    public class PersonalDataPersisterOptions extends GenericDbConfig<PersonalDataPersister.PersonalDataPersisterOptions> {

        @Override
        public PersonalDataPersisterOptions buildFromJsonElement(JsonElement json) {
            return PluginConfigHelper.buildFromJson(json, PersonalDataPersister.PersonalDataPersisterOptions.class);
        }
    }
}
