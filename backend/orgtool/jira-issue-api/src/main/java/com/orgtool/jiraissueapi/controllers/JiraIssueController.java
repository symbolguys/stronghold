package com.orgtool.jiraissueapi.controllers;

import com.orgtool.jiraissueapi.models.JiraIssueResponse;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class JiraIssueController {

    private final RabbitTemplate rabbitTemplate;
    private final Queue responseQueue;

    public JiraIssueController(RabbitTemplate rabbitTemplate, Queue responseQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.responseQueue = responseQueue;
    }

    @GetMapping("/issues/{issueKey}")
    public JiraIssueResponse getJiraIssue(@PathVariable String issueKey) {
        String correlationId = UUID.randomUUID().toString();
        Message response = rabbitTemplate.sendAndReceive("jira-issues-exchange", "jira-issues-routing-key",
                new Message(issueKey.getBytes(), MessagePropertiesBuilder.newInstance().setCorrelationId(correlationId).setReplyTo(responseQueue.getName()).build()));
        if (response != null && response.getBody() != null) {
            String responseStr = new String(response.getBody());
            JiraIssueResponse jiraIssueResponse = new JiraIssueResponse(responseStr);
            if (jiraIssueResponse.getCorrelationId().equals(correlationId)) {
                return jiraIssueResponse;
            } else {
                throw new RuntimeException("Invalid correlation ID received in response");
            }
        } else {
            throw new RuntimeException("No response received from server");
        }
    }

}