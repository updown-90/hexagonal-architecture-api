package com.updown.api.account.domain.value;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Getter
@EqualsAndHashCode
@Embeddable
public class AccountName {
    @Column(name = "account_name")
    private final String value;
    
    protected AccountName() {
        this.value = null;
    }
    
    public AccountName(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("계정명은 필수입니다");
        }
        this.value = value.trim();
    }
    
    public static AccountName of(String value) {
        return new AccountName(value);
    }
}
