package com.github.frapontillo.pulse.crowd.data.plugin;

import com.github.frapontillo.pulse.crowd.data.entity.PersonalData;
import com.github.frapontillo.pulse.crowd.data.repository.PersonalDataRepository;
import com.github.frapontillo.pulse.spi.IPlugin;
import com.github.frapontillo.pulse.spi.PluginConfigHelper;
import com.github.frapontillo.pulse.util.PulseLogger;
import com.google.gson.JsonElement;
import org.apache.logging.log4j.Logger;
import rx.Observable;
import rx.Subscriber;
import rx.observers.SafeSubscriber;

import java.util.Date;

/**
 * An implementation of {@link IPlugin} that fetch PersonalData from database.
 *
 * @author Cosimo Lovascio
 */
public class PersonalDataFetcher extends IPlugin<Object, PersonalData, PersonalDataFetcher.PersonalDataFetcherOptions> {

    public final static String PLUGIN_NAME = "personaldata-fetch";
    private PersonalDataRepository personalDataRepository;
    private final Logger logger = PulseLogger.getLogger(PersonalData.class);

    @Override
    public String getName() {
        return PLUGIN_NAME;
    }

    @Override
    public PersonalDataFetcher.PersonalDataFetcherOptions getNewParameter() {
        return new PersonalDataFetcherOptions();
    }

    @Override
    protected Observable.Operator<PersonalData, Object> getOperator(PersonalDataFetcher.PersonalDataFetcherOptions parameters) {
        personalDataRepository = new PersonalDataRepository(parameters.getDb());

        return subscriber -> new SafeSubscriber<>(new Subscriber<Object>() {
            @Override public void onCompleted() {

                Observable<PersonalData> dbPersonalData = personalDataRepository
                        .find(parameters.getSince(), parameters.getUntil(),
                                parameters.getSource());
                dbPersonalData.subscribe(subscriber);
            }

            @Override public void onError(Throwable e) {
                e.printStackTrace();
                subscriber.onError(e);
            }

            @Override public void onNext(Object o) {
                // do absolutely nothing
            }
        });
    }


    /**
     * Fetching options that include the database name from {@link GenericDbConfig}.
     */
    public class PersonalDataFetcherOptions extends GenericDbConfig<PersonalDataFetcher.PersonalDataFetcherOptions> {
        private Date since;
        private Date until;
        private String source;

        public Date getSince() {
            return since;
        }

        public void setSince(Date since) {
            this.since = since;
        }

        public Date getUntil() {
            return until;
        }

        public void setUntil(Date until) {
            this.until = until;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        @Override
        public PersonalDataFetcher.PersonalDataFetcherOptions buildFromJsonElement(JsonElement json) {
            return PluginConfigHelper.buildFromJson(json, PersonalDataFetcher.PersonalDataFetcherOptions.class);
        }


    }
}
