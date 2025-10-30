package com.updown.api.account.domain.vo;

import lombok.Value;

/**
 * 로그인 ID 값 객체
 */
@Value
public class LoginId {
    String value;
    
    public static LoginId of(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("LoginId cannot be null or empty");
        }
        return new LoginId(value);
    }
}