package com.updown.api.account.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Password 값 객체 테스트
 */
class PasswordTest {

    @Test
    @DisplayName("유효한 비밀번호로 Password를 생성할 수 있다")
    void createValidPassword() {
        // given
        String validPassword = "password123";

        // when
        Password password = Password.of(validPassword);

        // then
        assertThat(password.getValue()).isEqualTo(validPassword);
    }

    @Test
    @DisplayName("null 비밀번호로 Password를 생성하면 예외가 발생한다")
    void createPasswordWithNull() {
        // when & then
        assertThatThrownBy(() -> Password.of(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Password cannot be null or empty");
    }

    @Test
    @DisplayName("빈 문자열로 Password를 생성하면 예외가 발생한다")
    void createPasswordWithEmpty() {
        // when & then
        assertThatThrownBy(() -> Password.of(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Password cannot be null or empty");
    }

    @Test
    @DisplayName("공백 문자열로 Password를 생성하면 예외가 발생한다")
    void createPasswordWithBlank() {
        // when & then
        assertThatThrownBy(() -> Password.of("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Password cannot be null or empty");
    }

    @Test
    @DisplayName("동일한 비밀번호로 생성된 Password 객체는 같다")
    void passwordEquality() {
        // given
        String passwordValue = "password123";
        Password password1 = Password.of(passwordValue);
        Password password2 = Password.of(passwordValue);

        // when & then
        assertThat(password1).isEqualTo(password2);
        assertThat(password1.hashCode()).isEqualTo(password2.hashCode());
    }

    @Test
    @DisplayName("다른 비밀번호로 생성된 Password 객체는 다르다")
    void passwordInequality() {
        // given
        Password password1 = Password.of("password123");
        Password password2 = Password.of("password456");

        // when & then
        assertThat(password1).isNotEqualTo(password2);
    }
}