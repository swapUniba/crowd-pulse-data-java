package com.github.frapontillo.pulse.crowd.data.plugin;

import com.github.frapontillo.pulse.crowd.data.entity.Connection;
import com.github.frapontillo.pulse.crowd.data.repository.ConnectionRepository;
import com.github.frapontillo.pulse.rx.PulseSubscriber;
import com.github.frapontillo.pulse.spi.IPlugin;
import com.github.frapontillo.pulse.spi.PluginConfigHelper;
import com.google.gson.JsonElement;
import org.bson.types.ObjectId;
import rx.Observable;

/**
 * An implementation of {@link IPlugin} that archivie Connection in database.
 *
 * @author Fabio De Pasquale
 */

public class ConnectionPersister extends IPlugin<Connection, Connection, ConnectionPersister.ConnectionPersisterOptions> {

    public final static String PLUGIN_NAME = "connection-persist";
    private ConnectionRepository connectionRepository;


    @Override
    public String getName() {
        return PLUGIN_NAME;
    }

    @Override
    public ConnectionPersisterOptions getNewParameter() {
        return new ConnectionPersisterOptions();
    }

    @Override
    protected Observable.Operator<Connection, Connection> getOperator(
            ConnectionPersisterOptions parameters) {

        // init the connection repository with the given target DB, if any
        connectionRepository = new ConnectionRepository(parameters.getDb());

        return subscriber -> new PulseSubscriber<Connection>(subscriber) {
            @Override public void onNext(Connection Connection) {
                ObjectId id = Connection.getId();
                reportElementAsStarted(id);

                connectionRepository.updateOrInsert(Connection);

                reportElementAsEnded(id);
                subscriber.onNext(Connection);
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


    public class ConnectionPersisterOptions extends GenericDbConfig<ConnectionPersister.ConnectionPersisterOptions> {

        @Override
        public ConnectionPersisterOptions buildFromJsonElement(JsonElement json) {
            return PluginConfigHelper.buildFromJson(json, ConnectionPersister.ConnectionPersisterOptions.class);
        }
    }
}
