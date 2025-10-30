package com.updown.api.common.exception;

import lombok.Getter;

/**
 * 커스텀 런타임 예외
 */
@Getter
public class CustomRuntimeException extends RuntimeException {
    
    private final ExceptionType exceptionType;
    
    public CustomRuntimeException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }
}
