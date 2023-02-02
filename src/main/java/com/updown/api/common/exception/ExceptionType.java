package com.updown.api.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionType {

    NOT_FOUND_USER("C404", "존재하지 않는 사용자 입니다.");

    private String code;
    private String message;

}
