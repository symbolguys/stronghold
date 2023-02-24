package com.orgtool.jiraissueapi.models;

public class JiraIssueResponse {

    private String correlationId;
    private String status;
    private String message;

    public JiraIssueResponse() {
    }

    public JiraIssueResponse(String responseStr) {
        String[] parts = responseStr.split(",", 3);
        this.correlationId = parts[0];
        this.status = parts[1];
        this.message = parts[2];
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return correlationId + "," + status + "," + message;
    }

}



