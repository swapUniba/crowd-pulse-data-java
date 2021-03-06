package com.github.frapontillo.pulse.crowd.data.plugin;

import com.github.frapontillo.pulse.crowd.data.entity.Message;
import com.github.frapontillo.pulse.spi.IPlugin;
import com.github.frapontillo.pulse.spi.IPluginConfig;
import com.github.frapontillo.pulse.spi.PluginConfigHelper;
import com.google.gson.JsonElement;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * Plugin to filter the messages in the pipeline according to the parameters specified in the
 * {@link MessageFilter.MessageFilterConfig} object passed to
 * the actual instance of the plugin.
 * <p/>
 * The following example configuration will select all messages with a sentiment in the
 * (-infty, -0,2) and (0.5, 0,7] ranges.
 * <p/>
 * {@code
 * {
 *   "sentiment: [{
 *     "lt": -0.2
 *   }, {
 *     "gt": 0.5,
 *     "lte": 0.7
 *   }]
 * }
 * }
 *
 * @author Francesco Pontillo
 * @see MessageFilter.MessageFilterConfig
 */
public class MessageFilter extends IPlugin<Message, Message, MessageFilter.MessageFilterConfig> {
    private final static String PLUGIN_NAME = "message-filter";

    @Override public String getName() {
        return PLUGIN_NAME;
    }

    public IPlugin<Message, Message, MessageFilterConfig> getInstance() {
        return new MessageFilter();
    }

    @Override public MessageFilterConfig getNewParameter() {
        return new MessageFilterConfig();
    }

    @Override
    protected Observable.Operator<Message, Message> getOperator(MessageFilterConfig parameters) {
        return null;
    }

    @Override
    public Observable.Transformer<Message, Message> transform(MessageFilterConfig params) {
        return messageObservable -> messageObservable.filter(m -> params.sentiment.stream()
                .anyMatch(doubleMatch -> doubleMatch.check(m.getSentiment())));
    }

    /**
     * Configuration class for the MessageFilter plugin. Filtering is available on the
     * {@link Message#getSentiment()} value, using one or more
     * {@link MessageFilter.MessageFilterConfig.Match} elements.
     * <p/>
     * If any of the sentiment matches are satisfied, the message will be accepted and notified to
     * the stream (conditions are joined with the OR operator).
     */
    public class MessageFilterConfig implements IPluginConfig<MessageFilterConfig> {
        private List<Match<Double>> sentiment;

        public MessageFilterConfig() {
            sentiment = new ArrayList<>();
        }

        @Override public MessageFilterConfig buildFromJsonElement(JsonElement json) {
            return PluginConfigHelper.buildFromJson(json, MessageFilterConfig.class);
        }

        /**
         * Generic match class for the element:
         * <p/>
         * <ul>
         * <li>eq: element equality</li>
         * <li>neq: element inequality</li>
         * <li>gt: element greater than the specified value</li>
         * <li>gte: element greater or equal than the specified value</li>
         * <li>lt: element less than the specified value</li>
         * <li>lte: element less or equal than the specified value</li>
         * </ul>
         * <p/>
         * <p/>
         * All the non-null conditions will be joined with the AND operator (all must be true).
         *
         * @param <T> Type of value to apply the match to.
         */
        public class Match<T extends Comparable<T>> {
            private T eq;
            private T neq;
            private T gt;
            private T gte;
            private T lt;
            private T lte;

            private boolean check(T value) {
                return ((value != null) &&
                        (eq == null || value.equals(eq)) &&
                        (neq == null || !value.equals(neq)) &&
                        (gt == null || value.compareTo(gt) > 0) &&
                        (gte == null || value.compareTo(gte) >= 0) &&
                        (lt == null || value.compareTo(lt) < 0) &&
                        (lte == null || value.compareTo(lte) <= 0));
            }
        }
    }
}
