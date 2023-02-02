package com.updown.api.common.exception;

public class CustomRuntimeException extends RuntimeException {

    private String code;
    private String message;

    public CustomRuntimeException(ExceptionType exceptionType) {
        this.code = exceptionType.getCode();
        this.message = exceptionType.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }


}
