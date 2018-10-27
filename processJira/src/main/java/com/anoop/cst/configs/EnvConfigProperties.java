package com.anoop.cst.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
// @ConfigurationProperties(prefix = "prefix_name")
@ConfigurationProperties
public class EnvConfigProperties {

    public String jiraBaseURL;
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