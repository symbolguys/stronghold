package com.orgtool.JiraEpics.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orgtool.JiraEpics.models.Epic;
import com.orgtool.JiraEpics.models.EpicsResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class JiraEpicService {
    private static final String auth = "Basic ZW5nZWx1bmQ5OUBsaXZlLmRrOkFUQVRUM3hGZkdGMF9TRU1RaDk5cW9uSFh6d255OW12ODdpOXc1LV9CREVOVnhGQk9KU3JVaTQxbTIzczlHNXdZQXdBM0hPLTY5Q0VKUEJnVWdKcE5zZ09ZQkoxNlhGdHB2TlNXMlhvWVFNLWNMbEd3WExMSmlmdXFGRGVKeERXM0IxX3pXNnNtb3pUeHJBY3NvRkJieFZXV0tQbVJaVzRZMVdJYUFGdm9feGRrX1hNcHp6X2d5RT0wQkYwOUJCMw==";


    public EpicsResponse getBoardEpics(String boardIdorKey) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://testsymbolguys.atlassian.net/rest/agile/1.0/board/" + boardIdorKey + "/epic"))
                .GET()
                .header("Authorization", auth)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());

        return new ObjectMapper().readValue(response.body(), EpicsResponse.class);
    }

    public Epic getEpic(String epicIdOrKey) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://testsymbolguys.atlassian.net/rest/2/epic/" + epicIdOrKey))
                .GET()
                .header("Authorization", auth)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        return new ObjectMapper().readValue(response.body(), Epic.class);
    }

    public void deleteEpic(String epicIdOrKey) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://testsymbolguys.atlassian.net/rest/api/2/issue/" + epicIdOrKey))
                .DELETE()
                .header("Authorization", auth)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
    }

    public void createEpic(Epic epic) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://testsymbolguys.atlassian.net/rest/api/2/issue"))
                .POST(HttpRequest.BodyPublishers.ofString(new ObjectMapper().writeValueAsString(epic)))
                .header("Authorization", auth)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.toString());
    }

    public void updateEpic(Epic epic) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://testsymbolguys.atlassian.net/rest/api/2/issue" + epic.getId()))
                .PUT(HttpRequest.BodyPublishers.ofString(new ObjectMapper().writeValueAsString(epic)))
                .header("Authorization", auth)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.toString());
    }

    public void getTask(String idOrKEy) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://testsymbolguys.atlassian.net/rest/api/3/task/" + idOrKEy))
                .GET()
                .header("Authorization", auth)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.toString());

    }
}
