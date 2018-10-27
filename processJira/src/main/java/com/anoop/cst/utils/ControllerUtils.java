package com.anoop.cst.utils;

import com.anoop.cst.exceptions.CSTJiraException;
import com.anoop.cst.exceptions.Error.ErrorTypeEnum;
import com.anoop.cst.models.SingleResult;

import org.slf4j.Logger;

public class ControllerUtils {

    public static SingleResult getResultError(Throwable t, Logger logger) {
        logger.error(t.getMessage(), t);
        if (t instanceof CSTJiraException) {
            return SingleResult.of(((CSTJiraException) t).getError());
        }
        return SingleResult.of(new CSTJiraException(ErrorTypeEnum.SYSTEM_ERROR, t.getMessage(),
                CSTJiraException.SYSTEM_ERROR_EXCEPTION_CODE).getError());
    }
}