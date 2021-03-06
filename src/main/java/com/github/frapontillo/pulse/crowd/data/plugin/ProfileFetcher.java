package com.github.frapontillo.pulse.crowd.data.plugin;

import com.github.frapontillo.pulse.crowd.data.entity.Profile;
import com.github.frapontillo.pulse.spi.IPlugin;
import com.github.frapontillo.pulse.spi.PluginConfigHelper;
import com.github.frapontillo.pulse.util.PulseLogger;
import com.google.gson.JsonElement;
import com.github.frapontillo.pulse.crowd.data.repository.ProfileRepository;
import org.apache.logging.log4j.Logger;
import rx.Observable;
import rx.Subscriber;
import rx.observers.SafeSubscriber;

/**
 * An implementation of {@link IPlugin} that, no matter the input stream, waits for its completion
 * and then emits all of the {@link Profile}s stored in the database, eventually completing or
 * erroring.
 * <p/>
 * Use this plugin for transforming any stream into a stream containing all previously stored
 * {@link Profile}s.
 *
 * @author Francesco Pontillo
 */
public class ProfileFetcher extends IPlugin<Object, Profile, ProfileFetcher.ProfileFetcherOptions> {
    public final static String PLUGIN_NAME = "profile-fetch";
    private ProfileRepository profileRepository;
    private final Logger logger = PulseLogger.getLogger(ProfileFetcher.class);

    @Override public String getName() {
        return PLUGIN_NAME;
    }

    public IPlugin<Object, Profile, ProfileFetcherOptions> getInstance() {
        return new ProfileFetcher();
    }

    @Override public ProfileFetcherOptions getNewParameter() {
        return new ProfileFetcherOptions();
    }

    @Override
    protected Observable.Operator<Profile, Object> getOperator(ProfileFetcherOptions parameters) {
        // use a custom db, if any
        profileRepository = new ProfileRepository(parameters.getDb());

        return subscriber -> new SafeSubscriber<>(new Subscriber<Object>() {
            @Override public void onCompleted() {
                // fetch all messages from the database and subscribe view the new subscriber
                Observable<Profile> dbProfiles = profileRepository.get();
                dbProfiles.subscribe(subscriber);
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
    public class ProfileFetcherOptions extends GenericDbConfig<ProfileFetcherOptions> {
        @Override public ProfileFetcherOptions buildFromJsonElement(JsonElement json) {
            return PluginConfigHelper.buildFromJson(json, ProfileFetcherOptions.class);
        }
    }

}
