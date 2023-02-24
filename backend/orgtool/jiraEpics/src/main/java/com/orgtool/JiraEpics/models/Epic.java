package com.orgtool.JiraEpics.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Epic {
    @JsonProperty
    private int id;
    @JsonProperty
    private String key;
    @JsonProperty
    private String self;
    @JsonProperty
    private String name;
    @JsonProperty
    private String summary;
    @JsonProperty
    private boolean done;

    public int getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getSelf() {
        return self;
    }

    public String getName() {
        return name;
    }

    public String getSummary() {
        return summary;
    }

    public boolean isDone() {
        return done;
    }

}
