package com.hackathon.orgroup.jiraIssues;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.orgroup.jiraIssues.models.JiraIssue;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.io.IOException;

@Service
public class JiraIssueService {
    final String credentials = "engelund99@live.dk:ATATT3xFfGF0MsdfUyzps8IaoJeKiMK2WaIA7p3d8fxEZD-_zwxNNBB7EUBkqBrrjoXADmonXkjYsMFzeE5Fm8ihs3bAQeJVm22V5uZusDldnT8s97WUM4laMC2t42wSfz8Y79dDKFQ1aCTgBYNR60BOMpr4YK85vkoHPDZavlr38v0pzhyt8X4=26CD3614"; // replace with your Jira API token or username and password

    public String getIssue(String issueIdOrKey) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://testsymbolguys.atlassian.net/rest/agile/1.0/issue/" + issueIdOrKey))
                .GET()
                .header("Authorization", "Basic " + encodedCredentials)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}

