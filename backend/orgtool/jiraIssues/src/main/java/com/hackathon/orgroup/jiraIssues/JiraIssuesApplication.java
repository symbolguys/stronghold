package com.hackathon.orgroup.jiraIssues;

import com.hackathon.orgroup.jiraIssues.models.JiraIssue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;


@SpringBootApplication
public class JiraIssuesApplication {

    public static void main(String[] args) {
        SpringApplication.run(JiraIssuesApplication.class, args);
    }
}



