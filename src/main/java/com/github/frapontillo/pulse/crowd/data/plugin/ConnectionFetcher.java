package com.github.frapontillo.pulse.crowd.data.plugin;

import com.github.frapontillo.pulse.crowd.data.entity.Connection;
import com.github.frapontillo.pulse.crowd.data.repository.ConnectionRepository;
import com.github.frapontillo.pulse.spi.IPlugin;
import com.github.frapontillo.pulse.spi.PluginConfigHelper;
import com.github.frapontillo.pulse.util.PulseLogger;
import com.google.gson.JsonElement;
import org.apache.logging.log4j.Logger;
import rx.Observable;
import rx.Subscriber;
import rx.observers.SafeSubscriber;

/**
 * An implementation of {@link IPlugin} that fetch Connection from database.
 *
 * @author Fabio De Pasquale
 */
public class ConnectionFetcher extends IPlugin<Object, Connection, ConnectionFetcher.ConnectionFetcherOptions> {

    public final static String PLUGIN_NAME = "connection-fetch";
    private ConnectionRepository connectionRepository;
    private final Logger logger = PulseLogger.getLogger(Connection.class);

    @Override
    public String getName() {
        return PLUGIN_NAME;
    }

    @Override
    public ConnectionFetcher.ConnectionFetcherOptions getNewParameter() {
        return new ConnectionFetcherOptions();
    }

    @Override
    protected Observable.Operator<Connection, Object> getOperator(ConnectionFetcher.ConnectionFetcherOptions parameters) {
        connectionRepository = new ConnectionRepository(parameters.getDb());

        return subscriber -> new SafeSubscriber<>(new Subscriber<Object>() {
            @Override public void onCompleted() {

                Observable<Connection> dbConnection = connectionRepository
                        .find(parameters.getDisplayName());
                dbConnection.subscribe(subscriber);
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
    public class ConnectionFetcherOptions extends GenericDbConfig<ConnectionFetcher.ConnectionFetcherOptions> {
        private String displayName;

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public ConnectionFetcher.ConnectionFetcherOptions buildFromJsonElement(JsonElement json) {
            return PluginConfigHelper.buildFromJson(json, ConnectionFetcher.ConnectionFetcherOptions.class);
        }

    }
}
