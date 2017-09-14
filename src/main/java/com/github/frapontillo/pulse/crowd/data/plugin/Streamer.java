package com.github.frapontillo.pulse.crowd.data.plugin;

import com.github.frapontillo.pulse.spi.IPlugin;
import com.github.frapontillo.pulse.spi.VoidConfig;
import rx.Observable;
import rx.Subscriber;
import rx.observers.SafeSubscriber;

/**
 * An implementation of {@link IPlugin} that has no effect on input elements, emitting them
 * untouched. Error and completion events are notified as well.
 *
 * @author Francesco Pontillo
 */
public class Streamer extends IPlugin<Object, Object, VoidConfig> {
    public final static String PLUGIN_NAME = "streamer";

    @Override public String getName() {
        return PLUGIN_NAME;
    }

    public IPlugin<Object, Object, VoidConfig> getInstance() {
        return new Streamer();
    }

    @Override public VoidConfig getNewParameter() {
        return new VoidConfig();
    }

    @Override protected Observable.Operator<Object, Object> getOperator(VoidConfig parameters) {
        return subscriber -> new SafeSubscriber<>(new Subscriber<Object>() {
            @Override public void onCompleted() {
                subscriber.onCompleted();
            }

            @Override public void onError(Throwable e) {
                subscriber.onError(e);
            }

            @Override public void onNext(Object o) {
                subscriber.onNext(o);
            }
        });
    }
}
