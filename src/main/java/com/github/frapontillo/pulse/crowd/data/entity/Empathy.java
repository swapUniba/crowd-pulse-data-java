package com.github.frapontillo.pulse.crowd.data.entity;

/**
 * Empathy class.
 */
public class Empathy extends Entity {

    private Long timestamp;
    private Double value;
    private Double confidence;
    private String source;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double score) {
        this.value = score;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
