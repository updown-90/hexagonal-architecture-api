package com.updown.api.account.domain.value;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class AccountId {
    private final Long value;
    
    public AccountId(Long value) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("AccountId는 양수여야 합니다");
        }
        this.value = value;
    }
    
    public static AccountId of(Long value) {
        return new AccountId(value);
    }
}
