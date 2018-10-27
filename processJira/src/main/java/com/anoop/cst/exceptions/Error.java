
package com.anoop.cst.exceptions;

public class Error {
    public enum ErrorTypeEnum {
        INVALID_REQUEST("INVALID_REQUEST"), SYSTEM_ERROR("SYSTEM_ERROR"), VALIDATION_ERROR("VALIDATION_ERROR"),
        STALE_STATE_ERROR("STALE_STATE_ERROR");
        private final String value;

        private ErrorTypeEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }

        @Override
        public String toString() {
            return value();
        }

        public static Error.ErrorTypeEnum fromValue(String value) {
            for (Error.ErrorTypeEnum e : values()) {
                if (e.value().equals(value)) {
                    return e;
                }
            }
            throw new IllegalArgumentException(("Illegal value: " + value));
        }

    }

    protected String code;
    protected Error.ErrorTypeEnum type;
    protected String message;
    protected String detail;

    public void setType(Error.ErrorTypeEnum type) {
        this.type = type;
    }

    public Error.ErrorTypeEnum getType() {
        return type;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
