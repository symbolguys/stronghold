package com.hackathon.orgroup.jiraIssues.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

@Service
public class JiraIssueService {
    @Value("${spring.jira.account}")
    private String account;

    @Value("${spring.jira.token}")
    private String token;

    public String getIssue(String issueIdOrKey) throws IOException, InterruptedException {
        String credentials = account + ":" + token;
        HttpClient client = HttpClient.newHttpClient();
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://testsymbolguys.atlassian.net/rest/api/2/issue/" + issueIdOrKey))
                .GET()
                .header("Authorization", "Basic " + encodedCredentials)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public void createIssue(JiraIssue issue) throws IOException, InterruptedException {
        String credentials = account + ":" + token;
        HttpClient client = HttpClient.newHttpClient();
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://testsymbolguys.atlassian.net/rest/api/2/issue"))
                .POST(HttpRequest.BodyPublishers.ofString(new ObjectMapper().writeValueAsString(epic)))
                .header("Authorization", "Basic " + encodedCredentials)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public void updateIssue(JiraIssue issue) throws IOException, InterruptedException {
        String credentials = account + ":" + token;
        HttpClient client = HttpClient.newHttpClient();
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://testsymbolguys.atlassian.net/rest/api/2/issue"))
                .PUT(HttpRequest.BodyPublishers.ofString(new ObjectMapper().writeValueAsString(epic)))
                .header("Authorization", "Basic " + encodedCredentials)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public void deleteIssue(String issueIdOrKey) {
        String credentials = account + ":" + token;
        HttpClient client = HttpClient.newHttpClient();
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://testsymbolguys.atlassian.net/rest/api/2/issue/" + issueIdOrKey))
                .DELETE()
                .header("Authorization", "Basic " + encodedCredentials)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public String getIssuesForEpic(String epicID) throws IOException, InterruptedException {
        String credentials = account + ":" + token;
        HttpClient client = HttpClient.newHttpClient();
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://testsymbolguys.atlassian.net/rest/agile/1.0/epic/" + epicID + "/issue"))
                .GET()
                .header("Authorization", "Basic " + encodedCredentials)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public String getIssueSubtasks(String issueIdOrKey) throws IOException, InterruptedException {
        String credentials = account + ":" + token;
        HttpClient client = HttpClient.newHttpClient();
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://testsymbolguys.atlassian.net/rest/api/2/issue/" + issueIdOrKey + "/subtask"))
                .GET()
                .header("Authorization", "Basic " + encodedCredentials)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}

