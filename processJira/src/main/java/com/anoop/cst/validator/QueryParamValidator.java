package com.anoop.cst.validator;

import java.util.Optional;

import com.anoop.cst.exceptions.CSTJiraException;
import com.anoop.cst.exceptions.Error.ErrorTypeEnum;

public class QueryParamValidator {

    public static void validate(Optional<String> query, Optional<String> queryDescription) {
        query.orElseThrow(() -> new CSTJiraException(ErrorTypeEnum.VALIDATION_ERROR, "query IS NULL",
                CSTJiraException.CALLER_ERROR_EXCEPTION_CODE));

        queryDescription.orElseThrow(() -> new CSTJiraException(ErrorTypeEnum.VALIDATION_ERROR, "name IS NULL",
                CSTJiraException.CALLER_ERROR_EXCEPTION_CODE));
    }
}