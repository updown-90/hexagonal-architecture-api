package com.updown.api.account.domain.vo;

import lombok.Value;

/**
 * 계정 ID 값 객체
 */
@Value
public class AccountId {
    Long value;
    
    public static AccountId of(Long value) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("AccountId must be positive");
        }
        return new AccountId(value);
    }
}