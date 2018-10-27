package com.anoop.cst.services;

import java.io.IOException;

import com.anoop.cst.clients.SQSClientWrapper;
import com.anoop.cst.models.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class SQSService {

    private final Logger logger = LoggerFactory.getLogger(StoryPointProcessorService.class);

    private final SQSClientWrapper sqsClient;
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    public SQSService(final SQSClientWrapper sqsClient) {
        this.sqsClient = sqsClient;
    }

    public void putMessage(Message message) {
        logger.info("Invoking SQS API with message={}", message);
        sqsClient.sendMessage(getJsonMessageString(message));
        sqsClient.recieveMessage();
    }

    public void putMessageAsync(Message message) {
        logger.info("Invoking SQS API with message={}", message);
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                long threadId = Thread.currentThread().getId();
                logger.info("Started thread={} to push message SQS", threadId);
                putMessage(message);
                logger.info("Finished pushing message to queue for thread={}", threadId);
            }
        });
    }

    protected String getJsonMessageString(Message message) {
        try {
            return mapper.writeValueAsString(message);
        } catch (IOException e) {
            throw new RuntimeException("Error while converting message to string");
        }
    }
}