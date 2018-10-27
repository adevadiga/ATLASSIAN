package com.anoop.cst.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.anoop.cst.exceptions.CSTJiraException;
import com.anoop.cst.exceptions.Error.ErrorTypeEnum;
import com.anoop.cst.models.Message;
import com.anoop.cst.models.SingleResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rx.functions.Func1;

@Component
public class StoryPointProcessorService {

    private final Logger logger = LoggerFactory.getLogger(StoryPointProcessorService.class);

    private final JiraService jiraService;
    private final SQSService sqsService;
    public static final Func1<Throwable, SingleResult> processErrorSingleResult = throwable -> SingleResult
            .of(new CSTJiraException(ErrorTypeEnum.SYSTEM_ERROR, throwable.getMessage(),
                    CSTJiraException.SYSTEM_ERROR_EXCEPTION_CODE).getError());

    @Autowired
    public StoryPointProcessorService(final JiraService jiraService, final SQSService sqsService) {
        this.jiraService = jiraService;
        this.sqsService = sqsService;
    }

    public SingleResult transform(String query, String name) {
        logAccessInfo("transform", query, name);
        AtomicBoolean errorState = new AtomicBoolean(false);

        long startTime = System.currentTimeMillis();
        List<Error> errors = new ArrayList<>();

        jiraService.getTotalStoryPoints(query).retry(3).doOnError((throwable) -> {
            logger.error("Error while computing Jira Story points", throwable);
            errorState.set(true);
        }).subscribe(totalStoryPoints -> postMessageToQueue(name, totalStoryPoints));

        logInteraction(this.getClass().getSimpleName(), "transform", errors.isEmpty() ? "success" : "failure",
                System.currentTimeMillis() - startTime);

        if (errorState.get()) {
            return new SingleResult("processJira", "500", "Failed to process.");
        }
        return new SingleResult("processJira", "200", "Succesfully processed.");
    }

    public void postMessageToQueue(String description, int storyPointTotal) {
        long startTime = System.currentTimeMillis();
        logAccessInfo("postMessageToQueue", description, String.valueOf(storyPointTotal));

        Message message = new Message(description, storyPointTotal);
        sqsService.putMessageAsync(message);
        // sqsService.putMessage(message);

        logInteraction(this.getClass().getSimpleName(), "postMessageToQueue", "success",
                System.currentTimeMillis() - startTime);
    }

    protected void logInteraction(String provider, String operation, String status, long totalTime) {
        logger.info("interaction={}; operation={}; status={}; executionTime={}", provider + "-interaction", operation,
                status, totalTime);
    }

    protected void logAccessInfo(String operation, String query, String descriptionName) {
        logger.info("Access event: operation={}; for query={}; descrption={}", operation, query, descriptionName);
    }
}