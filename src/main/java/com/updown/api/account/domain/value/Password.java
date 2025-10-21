package com.updown.api.account.domain.value;

import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Getter
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
        String encrypted = PASSWORD_ENCODER.encode(plainPassword);
        return new Password(encrypted);
    }
    
    public static Password ofEncrypted(String encryptedPassword) {
        return new Password(encryptedPassword);
    }
    
    public boolean matches(String plainPassword) {
        return PASSWORD_ENCODER.matches(plainPassword, encryptedValue);
    }
}

