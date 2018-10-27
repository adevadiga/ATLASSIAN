package com.anoop.cst.configs;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DevConfig {

    @Bean
    // @Qualifier("templates")
    public AmazonSQS dynamoDBClient(EnvConfigProperties envConfigProperties) {
        String endpoint = "http://localhost:9324";
        String region = "elasticmq";
        String accessKey = "x";
        String secretKey = "x";

        AmazonSQS client = AmazonSQSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, region)).build();

        return client;
    }

}