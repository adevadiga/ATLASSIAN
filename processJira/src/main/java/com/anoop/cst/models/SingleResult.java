package com.anoop.cst.models;

import com.anoop.cst.exceptions.Error;

public class SingleResult {
    Object value;
    String operation;
    String code;
    String message;

    public SingleResult(String operation, String code, String message) {
        this.message = message;
        this.code = code;
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static SingleResult of(Error error) {
        return new SingleResult("Error", error.getCode(), error.getMessage());
    }
}
