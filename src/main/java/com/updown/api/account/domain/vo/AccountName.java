package com.updown.api.account.domain.vo;

import lombok.Value;

/**
 * 계정명 값 객체
 */
@Value
public class AccountName {
    String value;
    
    public static AccountName of(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("AccountName cannot be null or empty");
        }
        return new AccountName(value);
    }
}