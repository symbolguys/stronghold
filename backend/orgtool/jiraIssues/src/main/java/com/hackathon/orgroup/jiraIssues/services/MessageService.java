package com.hackathon.orgroup.jiraIssues.services;

import com.hackathon.orgroup.jiraIssues.models.QueueMessage;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class MessageService {

    private final RabbitTemplate rabbitTemplate;
    private final Queue messageQueue;
    private final JiraIssueService jiraIssueService;

    public MessageService(RabbitTemplate rabbitTemplate, Queue messageQueue, JiraIssueService jiraIssueService) {
        this.rabbitTemplate = rabbitTemplate;
        this.messageQueue = messageQueue;
        this.jiraIssueService = jiraIssueService;
    }

    @PostConstruct
    public void startListening() {
        new Thread(() -> {
            while (true) {
                Message message = rabbitTemplate.receive(messageQueue.getName());
                if (message != null) {
                    try (Connection connection = rabbitTemplate.getConnectionFactory().createConnection();
                         Channel channel = connection.createChannel(false)) {
                        handleMessage(message, channel, channel.getNextPublishSeqNo());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void handleMessage(Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        String issueIdOrKey = new String(message.getBody());
        QueueMessage response = new QueueMessage();
        channel.basicAck(tag, false);
        try {
            String issue = jiraIssueService.getIssue(issueIdOrKey);
            response.setMessage(issue);
            response.setStatus("Successful");
        } catch (Exception e) {
            response.setStatus("Failed");
        } finally {
            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setCorrelationId(message.getMessageProperties().getCorrelationId());
            Message responseMessage = new Message(response.toString().getBytes(), messageProperties);
            rabbitTemplate.send(message.getMessageProperties().getReplyTo(), responseMessage);
        }
    }
}