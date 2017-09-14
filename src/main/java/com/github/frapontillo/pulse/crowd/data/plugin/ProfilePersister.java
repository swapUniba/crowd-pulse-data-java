package com.github.frapontillo.pulse.crowd.data.plugin;

import com.github.frapontillo.pulse.rx.PulseSubscriber;
import com.github.frapontillo.pulse.spi.IPlugin;
import com.github.frapontillo.pulse.spi.PluginConfigHelper;
import com.github.frapontillo.pulse.util.PulseLogger;
import com.google.gson.JsonElement;
import com.github.frapontillo.pulse.crowd.data.entity.Profile;
import com.github.frapontillo.pulse.crowd.data.repository.ProfileRepository;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import rx.Observable;

/**
 * An implementation of {@link IPlugin} that persists all streamed {@link Profile}s, eventually
 * completing or erroring.
 *
 * @author Francesco Pontillo
 */
public class ProfilePersister
        extends IPlugin<Profile, Profile, ProfilePersister.ProfilePersisterOptions> {
    public final static String PLUGIN_NAME = "profile-persist";
    private ProfileRepository profileRepository;
    private final Logger logger = PulseLogger.getLogger(ProfilePersister.class);

    @Override public String getName() {
        return PLUGIN_NAME;
    }

    public IPlugin<Profile, Profile, ProfilePersisterOptions> getInstance() {
        return new ProfilePersister();
    }

    @Override public ProfilePersisterOptions getNewParameter() {
        return new ProfilePersisterOptions();
    }

    @Override protected Observable.Operator<Profile, Profile> getOperator(
            ProfilePersisterOptions parameters) {
        profileRepository = new ProfileRepository(parameters.getDb());
        return subscriber -> new PulseSubscriber<Profile>(subscriber) {
            @Override public void onNext(Profile profile) {
                ObjectId id = profile.getId();
                reportElementAsStarted(id);

                Profile originalProfile = profileRepository.getByUsername(profile.getUsername());
                // if the profile was already persisted change what might have been changed in time
                if (originalProfile != null) {
                    // followers and followings
                    originalProfile.setFollowers(profile.getFollowers());
                    originalProfile.setFollowings(profile.getFollowings());
                    // language and location
                    originalProfile.setLanguage(profile.getLanguage());
                    originalProfile.setLocation(profile.getLocation());
                    
                    if(profile.getLongitude() != null && profile.getLatitude() != null){
	                    originalProfile.setLatitude(profile.getLatitude());
	                    originalProfile.setLongitude(profile.getLongitude());
                    }
                    profile = originalProfile;
                }

                profileRepository.save(profile);

                reportElementAsEnded(id);
                subscriber.onNext(profile);
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

    /**
     * Profile persisting options that include the database name from {@link GenericDbConfig}.
     */
    public class ProfilePersisterOptions extends GenericDbConfig<ProfilePersisterOptions> {
        @Override public ProfilePersisterOptions buildFromJsonElement(JsonElement json) {
            return PluginConfigHelper.buildFromJson(json, ProfilePersisterOptions.class);
        }
    }

}
