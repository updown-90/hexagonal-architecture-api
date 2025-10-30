package com.updown.api.account.domain.vo;

import lombok.Value;

/**
 * 비밀번호 값 객체
 */
@Value
public class Password {
    String value;
    
    public static Password of(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        return new Password(value);
    }
}