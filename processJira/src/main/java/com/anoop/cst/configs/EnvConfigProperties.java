package com.anoop.cst.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
// @ConfigurationProperties(prefix = "prefix_name")
@ConfigurationProperties
public class EnvConfigProperties {

    @Value("${JIRA_BASE_URL}")
    public String jiraBaseURL;

    @Value("${QUEUE_URL}")
    public String queueURL;

    public String getJiraBaseURL() {
        return jiraBaseURL;
    }

    public void setJiraBaseURL(String jiraBaseURL) {
        this.jiraBaseURL = jiraBaseURL;
    }

    public String getQueueURL() {
        return queueURL;
    }

    public void setQueueURL(String queueURL) {
        this.queueURL = queueURL;
    }

}