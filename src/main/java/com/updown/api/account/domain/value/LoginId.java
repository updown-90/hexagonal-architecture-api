package com.updown.api.account.domain.value;

import lombok.Getter;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Getter
@Embeddable
public class LoginId {
    @Column(name = "login_id")
    private final String value;
    
    protected LoginId() {
        this.value = null;
    }
    
    public LoginId(String value) {
        this.value = value;
    }
    
    public static LoginId of(String value) {
        return new LoginId(value);
    }
}
