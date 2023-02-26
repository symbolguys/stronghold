package com.orgtool.JiraEpics.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orgtool.JiraEpics.models.JiraIssue;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class JiraIssueService {
    private static final String auth = "Basic ZW5nZWx1bmQ5OUBsaXZlLmRrOkFUQVRUM3hGZkdGMF9TRU1RaDk5cW9uSFh6d255OW12ODdpOXc1LV9CREVOVnhGQk9KU3JVaTQxbTIzczlHNXdZQXdBM0hPLTY5Q0VKUEJnVWdKcE5zZ09ZQkoxNlhGdHB2TlNXMlhvWVFNLWNMbEd3WExMSmlmdXFGRGVKeERXM0IxX3pXNnNtb3pUeHJBY3NvRkJieFZXV0tQbVJaVzRZMVdJYUFGdm9feGRrX1hNcHp6X2d5RT0wQkYwOUJCMw==";

    public String getIssue(String issueIdOrKey) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://testsymbolguys.atlassian.net/rest/api/2/issue/" + issueIdOrKey))
                .GET()
                .header("Authorization", auth)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
        return response.body();
    }

    public void createIssue(JiraIssue issue) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://testsymbolguys.atlassian.net/rest/api/2/issue"))
                .POST(HttpRequest.BodyPublishers.ofString(new ObjectMapper().writeValueAsString(issue)))
                .header("Authorization", auth)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public void updateIssue(JiraIssue issue) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://testsymbolguys.atlassian.net/rest/api/2/issue"))
                .PUT(HttpRequest.BodyPublishers.ofString(new ObjectMapper().writeValueAsString(issue)))
                .header("Authorization", auth)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public void deleteIssue(String issueIdOrKey) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://testsymbolguys.atlassian.net/rest/api/2/issue/" + issueIdOrKey))
                .DELETE()
                .header("Authorization", auth)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public String getIssuesForEpic(String epicID) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://testsymbolguys.atlassian.net/rest/agile/1.0/epic/" + epicID + "/issue"))
                .GET()
                .header("Authorization", auth)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public String getIssueSubtasks(String issueIdOrKey) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://testsymbolguys.atlassian.net/rest/api/2/issue/" + issueIdOrKey + "/subtask"))
                .GET()
                .header("Authorization", auth)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}