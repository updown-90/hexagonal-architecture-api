package com.updown.api.account.domain.value;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Getter
@EqualsAndHashCode
@Embeddable
public class Password {
    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
    
    @Column(name = "password")
    private final String encryptedValue;
    
    protected Password() {
        this.encryptedValue = null;
    }
    
    public Password(String encryptedValue) {
        this.encryptedValue = encryptedValue;
    }
    
    public static Password of(String plainPassword) {
        if (plainPassword == null || plainPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("비밀번호는 필수입니다");
        }
        String encrypted = PASSWORD_ENCODER.encode(plainPassword.trim());
        return new Password(encrypted);
    }
    
    public static Password ofEncrypted(String encryptedPassword) {
        return new Password(encryptedPassword);
    }
    
    public boolean matches(String plainPassword) {
        return PASSWORD_ENCODER.matches(plainPassword, encryptedValue);
    }
}

