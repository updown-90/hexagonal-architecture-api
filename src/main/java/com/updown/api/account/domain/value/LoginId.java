package com.updown.api.account.domain.value;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Getter
@EqualsAndHashCode
@Embeddable
public class LoginId {
    @Column(name = "login_id")
    private final String value;
    
    protected LoginId() {
        this.value = null;
    }
    
    public LoginId(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("로그인 ID는 필수입니다");
        }
        this.value = value.trim();
    }
    
    public static LoginId of(String value) {
        return new LoginId(value);
    }
}
