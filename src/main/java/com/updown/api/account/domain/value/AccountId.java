package com.updown.api.account.domain.value;

import lombok.Getter;

@Getter
public class AccountId {
    private final Long value;
    
    public AccountId(Long value) {
        this.value = value;
    }
    
    public static AccountId of(Long value) {
        return new AccountId(value);
    }
}
