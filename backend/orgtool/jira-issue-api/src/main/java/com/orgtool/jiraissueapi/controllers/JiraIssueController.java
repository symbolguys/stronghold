package com.orgtool.jiraissueapi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orgtool.jiraissueapi.models.JiraIssue;
import com.orgtool.jiraissueapi.models.QueueMessage;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class JiraIssueController {

    @Value("${spring.rabbitmq.exchange.routingKey}")
    String routingKey;

    @Value("${spring.rabbitmq.exchange}")
    String exchange;

    private final RabbitTemplate rabbitTemplate;
    private final Queue responseQueue;

    public JiraIssueController(RabbitTemplate rabbitTemplate, Queue responseQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.responseQueue = responseQueue;
    }

    @GetMapping("/issues/{issueKey}")
    public JiraIssue getJiraIssue(@PathVariable String issueKey) throws JsonProcessingException {
        rabbitTemplate.setReceiveTimeout(3000);
        String correlationId = UUID.randomUUID().toString();
        Message response = rabbitTemplate.sendAndReceive(exchange, routingKey,
                new Message(issueKey.getBytes(), MessagePropertiesBuilder.newInstance().setCorrelationId(correlationId).setReplyTo(responseQueue.getName()).build()));
        if (response != null && response.getBody() != null) {
            String responseStr = new String(response.getBody());
            QueueMessage queueMessage = new QueueMessage(responseStr);
            if (queueMessage.getCorrelationId().equals(correlationId)) {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(queueMessage.getMessage(), JiraIssue.class);
            } else {
                throw new RuntimeException("Invalid correlation ID received in response");
            }
        } else {
            throw new RuntimeException("No response received from server");
        }
    }

}