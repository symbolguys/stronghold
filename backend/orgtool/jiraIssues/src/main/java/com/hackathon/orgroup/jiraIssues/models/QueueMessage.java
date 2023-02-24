package com.hackathon.orgroup.jiraIssues.models;

public class QueueMessage {

    private String correlationId;
    private String status;
    private String message;

    public QueueMessage() {
    }

    public QueueMessage(String responseStr) {
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



