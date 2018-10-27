package com.anoop.cst.controller;

import java.util.Optional;

import com.anoop.cst.models.SingleResult;
import com.anoop.cst.services.StoryPointProcessorService;
import com.anoop.cst.utils.ControllerUtils;
import com.anoop.cst.validator.QueryParamValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/issue/")
public class JiraController {

    private final Logger logger = LoggerFactory.getLogger(JiraController.class);

    private final StoryPointProcessorService storyPointProcessorService;

    @Autowired
    public JiraController(final StoryPointProcessorService storyPointProcessorService) {
        this.storyPointProcessorService = storyPointProcessorService;
    }

    @RequestMapping(value = "sum", method = RequestMethod.GET, produces = "application/json")
    public SingleResult index(@RequestParam(value = "query") Optional<String> query,
            @RequestParam(value = "name") Optional<String> queryDescription) {

        try {
            QueryParamValidator.validate(query, queryDescription);
            return storyPointProcessorService.transform(query.get(), queryDescription.get());
        } catch (Throwable t) {
            return ControllerUtils.getResultError(t, logger);
        }
    }

}