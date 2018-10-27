package com.anoop.cst.exceptions;

import com.anoop.cst.exceptions.Error;
import com.anoop.cst.exceptions.Error.ErrorTypeEnum;

public class CSTJiraException extends RuntimeException {

    public static final String SYSTEM_ERROR_EXCEPTION_CODE = "CST-1000";
    public static final String CALLER_ERROR_EXCEPTION_CODE = "CST-2000";
    public static final String JIRA_SERVICE_ERROR_CODE = "CST-1000";

    private final Error error;

    public CSTJiraException(final ErrorTypeEnum errorTypeEnum, final String message, final String code) {
        super(message);
        error = new Error();
        error.setType(errorTypeEnum);
        error.setMessage(message);
        error.setCode(code);
    }

    public Error getError() {
        return error;
    }
}