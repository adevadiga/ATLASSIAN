package com.anoop.cst.configs;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.AmazonSQSException;
import com.amazonaws.services.sqs.model.CreateQueueResult;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({ "default" })
public class DevConfig {

    private static final String QUEUE_NAME = "anoop";

    @Bean
    public AmazonSQS sqsClient(EnvConfigProperties envConfigProperties) {
        String endpoint = "http://localhost:9324";
        String region = "elasticmq";
        String accessKey = "x";
        String secretKey = "x";

        AmazonSQS client = AmazonSQSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, region)).build();

        try {
            CreateQueueResult create_result = client.createQueue(QUEUE_NAME);
            String queueURL = client.getQueueUrl(QUEUE_NAME).getQueueUrl();
            System.out.println("Queue URL " + queueURL);
            // Hack: Override queueURL for now
            envConfigProperties.setQueueURL(queueURL);
        } catch (AmazonSQSException e) {
            if (!e.getErrorCode().equals("QueueAlreadyExists")) {
                throw e;
            }
        }

        return client;
    }

}