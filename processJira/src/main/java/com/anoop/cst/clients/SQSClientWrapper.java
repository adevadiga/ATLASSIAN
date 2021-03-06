package com.anoop.cst.clients;

import java.util.List;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.SendMessageBatchRequest;
import com.amazonaws.services.sqs.model.SendMessageBatchRequestEntry;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.anoop.cst.configs.EnvConfigProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SQSClientWrapper {

    private final AmazonSQS amazonSQS;
    private final String queueURL;

    @Autowired
    SQSClientWrapper(final AmazonSQS amazonSQS, final EnvConfigProperties envConfigProperties) {
        this.amazonSQS = amazonSQS;
        queueURL = envConfigProperties.getQueueURL();
    }

    public void sendMessage(String message) {
        SendMessageRequest sendMessageRequest = new SendMessageRequest().withQueueUrl(queueURL).withMessageBody(message)
                .withDelaySeconds(5);
        SendMessageResult result = this.amazonSQS.sendMessage(sendMessageRequest);
        System.out.println(result);
    }

    public void recieveMessage() {
        List<Message> messages = amazonSQS.receiveMessage(queueURL).getMessages();
        messages.stream().forEach(System.out::println);
    }

    public void sendMessageBatch(String message) {
        SendMessageBatchRequest send_batch_request = new SendMessageBatchRequest().withQueueUrl(queueURL).withEntries(
                new SendMessageBatchRequestEntry("msg_1", "Hello from message 1"),
                new SendMessageBatchRequestEntry("msg_2", "Hello from message 2").withDelaySeconds(10));
        amazonSQS.sendMessageBatch(send_batch_request);
    }
}