package com.hackathon.orgroup.jiraIssues.models;

import java.util.Date;
import java.util.List;
import java.util.Map;


public class JiraIssue {
    private String id;
    private String key;
    private String sprint;
    private String projectId;
    private String projectKey;
    private String projectName;
    private List<String> Epics;
    private String priorityName;
    private String status;
    private String summary;
    private List<String> comments;
    private String progress;

    public JiraIssue() {

    }
    public JiraIssue(String expand, String id, String key, Map<String, Object> fields) {
        this.id = id;
        this.key = key;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSprint() {
        return sprint;
    }

    public void setSprint(String sprint) {
        this.sprint = sprint;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<String> getEpics() {
        return Epics;
    }

    public void setEpics(List<String> epics) {
        Epics = epics;
    }

    public String getPriorityName() {
        return priorityName;
    }

    public void setPriorityName(String priorityName) {
        this.priorityName = priorityName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }


}