package com.anoop.cst.configs;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({ "prod" })
public class AwsConfig {

    @Bean
    public AmazonSQS sqsClient(EnvConfigProperties envConfigProperties) {
        AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
        return sqs;
    }

}