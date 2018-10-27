package com.anoop.cst.services;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.anoop.cst.configs.EnvConfigProperties;
import com.anoop.cst.models.JiraIssue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JiraService {

    private final Logger logger = LoggerFactory.getLogger(StoryPointProcessorService.class);

    private final EnvConfigProperties properties;
    private final JiraBasicAPI jiraBasicAPI;

    @Autowired
    JiraService(final EnvConfigProperties properties) {
        this.properties = properties;
        logger.info("Initilizing JiaService API JiraBaseURL={}", properties.jiraBaseURL);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(properties.jiraBaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

        jiraBasicAPI = retrofit.create(JiraBasicAPI.class);
    }

    Observable<Integer> getTotalStoryPoints(String query) {
        logger.info("Invoking Jira API with query={}", query);
        return jiraBasicAPI.getIssues(query).flatMapIterable(x -> x).filter(Objects::nonNull)
                .map(jiraIssue -> jiraIssue.getFields().getStoryPoints()).reduce((sum, storyPoint) -> sum + storyPoint);
    }

    Observable<List<JiraIssue>> getIssues(String query) throws IOException {
        Observable<List<JiraIssue>> repos = jiraBasicAPI.getIssues(query).asObservable();
        return repos;
    }
}