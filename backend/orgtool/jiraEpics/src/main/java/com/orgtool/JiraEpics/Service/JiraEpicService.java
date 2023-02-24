package com.orgtool.JiraEpics.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orgtool.JiraEpics.models.Epic;
import com.orgtool.JiraEpics.models.EpicsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

@Service
public class JiraEpicService {
    @Value("${spring.jira.account}")
    private String account;

    @Value("${spring.jira.token}")
    private String token;

    public EpicsResponse getBoardEpics(String boardIdorKey) throws IOException, InterruptedException {
        String credentials = account + ":" + token;
        HttpClient client = HttpClient.newHttpClient();
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://testsymbolguys.atlassian.net/rest/agile/1.0/board/" + boardIdorKey + "/epic"))
                .GET()
                .header("Authorization", "Basic" + encodedCredentials)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        return new ObjectMapper().readValue(response.body(), EpicsResponse.class);
    }

    public Epic getEpic(String epicIdOrKey) throws IOException, InterruptedException {
        String credentials = account + ":" + token;
        HttpClient client = HttpClient.newHttpClient();
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://testsymbolguys.atlassian.net/rest/agile/1.0/epic/" + epicIdOrKey))
                .GET()
                .header("Authorization", "Basic" + encodedCredentials)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        return new ObjectMapper().readValue(response.body(), Epic.class);
    }

    public void deleteEpic(String epicIdOrKey) throws IOException, InterruptedException {
        String credentials = account + ":" + token;
        HttpClient client = HttpClient.newHttpClient();
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://testsymbolguys.atlassian.net/rest/api/2/issue/" + epicIdOrKey))
                .DELETE()
                .header("Authorization", "Basic " + encodedCredentials)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
    }

    public void getIssuesForEpic(String epicIdOrKey) throws IOException, InterruptedException {
        String credentials = account + ":" + token;
        HttpClient client = HttpClient.newHttpClient();
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://testsymbolguys.atlassian.net/rest/agile/1.0/epic/" + epicIdOrKey + "/issue"))
                .GET()
                .header("Authorization", "Basic " + encodedCredentials)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.toString());
        //return new ObjectMapper().readValue(response.body(), Epic.class);
    }


    public void createEpic(Epic epic) throws IOException, InterruptedException {
        String credentials = account + ":" + token;
        HttpClient client = HttpClient.newHttpClient();
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://testsymbolguys.atlassian.net/rest/api/2/issue"))
                .POST(HttpRequest.BodyPublishers.ofString(new ObjectMapper().writeValueAsString(epic)))
                .header("Authorization", "Basic " + encodedCredentials)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.toString());
    }

    public void updateEpic(Epic epic) throws IOException, InterruptedException {
        String credentials = account + ":" + token;
        HttpClient client = HttpClient.newHttpClient();
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://testsymbolguys.atlassian.net/rest/api/2/issue"))
                .PUT(HttpRequest.BodyPublishers.ofString(new ObjectMapper().writeValueAsString(epic)))
                .header("Authorization", "Basic " + encodedCredentials)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.toString());
    }
}
