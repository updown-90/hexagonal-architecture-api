package com.updown.api.account.domain.value;

import lombok.Getter;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Getter
@Embeddable
public class AccountName {
    @Column(name = "account_name")
    private final String value;
    
    protected AccountName() {
        this.value = null;
    }
    
    public AccountName(String value) {
        this.value = value;
    }
    
    public static AccountName of(String value) {
        return new AccountName(value);
    }
}
