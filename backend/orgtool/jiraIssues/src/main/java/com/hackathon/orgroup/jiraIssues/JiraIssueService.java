package com.hackathon.orgroup.jiraIssues;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.orgroup.jiraIssues.models.JiraIssue;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

@Service
public class JiraIssueService {

    public JiraIssue getIssue(String issueIdOrKey) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String credentials = "engelund99@live.dk:ATATT3xFfGF0esRjcu5L-9c3N1Ai4Z2qn2SpO0w6GPHLiQT3BT5OKY6ZlOhTC7iSaPfZqajJLfhPaZMtWKP0jPsXGOWZjy8UaNvwkULSfDPKThbFuVm6oNEYAfP7QLydI7cyYEwThA4YbUjnIJU61ScI6CYDELrR5Aexh0OAf4diMHr-2WN9Q7I=9B998687"; // replace with your Jira API token or username and password

        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://testsymbolguys.atlassian.net/rest/agile/1.0/issue/" + issueIdOrKey))
                .GET()
                .header("Authorization", "Basic " + encodedCredentials)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new ObjectMapper().readValue(response.body(), JiraIssue.class);
    }
}

