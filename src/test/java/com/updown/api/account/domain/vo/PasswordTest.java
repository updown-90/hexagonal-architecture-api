package com.updown.api.account.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PasswordTest {

    @Test
    @DisplayName("Password를 생성할 수 있다")
    void createPassword() {
        // given
        String plainPassword = "password123";

        // when
        Password password = Password.of(plainPassword);

        // then
        assertThat(password.getEncryptedValue()).isNotNull();
        assertThat(password.getEncryptedValue()).isNotEmpty();
    }

    @Test
    @DisplayName("null 값으로 Password를 생성하면 예외가 발생한다")
    void createPasswordWithNull() {
        // when & then
        assertThatThrownBy(() -> Password.of(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비밀번호는 필수입니다");
    }

    @Test
    @DisplayName("빈 문자열로 Password를 생성하면 예외가 발생한다")
    void createPasswordWithEmptyString() {
        // when & then
        assertThatThrownBy(() -> Password.of(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비밀번호는 필수입니다");
    }

    @Test
    @DisplayName("공백만 있는 문자열로 Password를 생성하면 예외가 발생한다")
    void createPasswordWithBlankString() {
        // when & then
        assertThatThrownBy(() -> Password.of("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비밀번호는 필수입니다");
    }

    @Test
    @DisplayName("암호화된 비밀번호로 Password를 생성할 수 있다")
    void createPasswordWithEncrypted() {
        // given
        String encryptedPassword = "$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi";

        // when
        Password password = Password.ofEncrypted(encryptedPassword);

        // then
        assertThat(password.getEncryptedValue()).isEqualTo(encryptedPassword);
    }

    @Test
    @DisplayName("비밀번호가 일치하는지 확인할 수 있다")
    void matches() {
        // given
        String plainPassword = "password123";
        Password password = Password.of(plainPassword);

        // when & then
        assertThat(password.matches(plainPassword)).isTrue();
        assertThat(password.matches("wrongpassword")).isFalse();
    }

    @Test
    @DisplayName("암호화된 비밀번호로 생성한 Password도 일치 확인이 가능하다")
    void matchesWithEncrypted() {
        // given
        String plainPassword = "password123";
        Password originalPassword = Password.of(plainPassword);
        String encryptedValue = originalPassword.getEncryptedValue();
        
        // when
        Password recreatedPassword = Password.ofEncrypted(encryptedValue);

        // then
        assertThat(recreatedPassword.matches(plainPassword)).isTrue();
    }

    @Test
    @DisplayName("같은 암호화된 값을 가진 Password는 동등하다")
    void equalsAndHashCode() {
        // given
        String encryptedPassword = "$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi";
        Password password1 = Password.ofEncrypted(encryptedPassword);
        Password password2 = Password.ofEncrypted(encryptedPassword);

        // when & then
        assertThat(password1).isEqualTo(password2);
        assertThat(password1.hashCode()).isEqualTo(password2.hashCode());
    }

    @Test
    @DisplayName("다른 암호화된 값을 가진 Password는 동등하지 않다")
    void notEquals() {
        // given
        Password password1 = Password.of("password1");
        Password password2 = Password.of("password2");

        // when & then
        assertThat(password1).isNotEqualTo(password2);
    }
}
