package com.updown.api.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 예외 타입 열거형
 */
@Getter
@AllArgsConstructor
public enum ExceptionType {
    
    NOT_FOUND_USER("C404", "존재하지 않는 사용자입니다."),
    NOT_NORMAL_USER("C405", "정상적인 계정이 아닙니다."),
    DUPLICATE_USER("C409", "이미 존재하는 사용자입니다."),
    NOT_FOUND_DEPARTMENT("D404", "존재하지 않는 부서입니다."),
    DUPLICATE_DEPARTMENT("D409", "이미 존재하는 부서입니다.");
    
    private final String code;
    private final String message;
}
