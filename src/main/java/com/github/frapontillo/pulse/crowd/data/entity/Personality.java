package com.github.frapontillo.pulse.crowd.data.entity;

public class Personality extends Entity {

    private Long timestamp;
    private Double openness;
    private Double conscientiousness;
    private Double extroversion;
    private Double agreeableness;
    private Double neuroticism;
    private Double confidence;
    private String source;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
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
