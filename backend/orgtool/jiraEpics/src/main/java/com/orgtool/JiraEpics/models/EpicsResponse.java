package com.orgtool.JiraEpics.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EpicsResponse {
    @JsonProperty
    private int maxResults;
    @JsonProperty
    private int startAt;
    @JsonProperty
    private boolean isLast;
    @JsonProperty
    private List<Epic> epics;

    public int getMaxResults() {
        return maxResults;
    }

    public int getStartAt() {
        return startAt;
    }

    public boolean isLast() {
        return isLast;
    }

    public List<Epic> getEpics() {
        return epics;
    }


}
