package com.updown.api.account.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * LoginId 값 객체 테스트
 */
class LoginIdTest {

    @Test
    @DisplayName("유효한 로그인 ID로 LoginId를 생성할 수 있다")
    void createValidLoginId() {
        // given
        String validLoginId = "testuser";

        // when
        LoginId loginId = LoginId.of(validLoginId);

        // then
        assertThat(loginId.getValue()).isEqualTo(validLoginId);
    }

    @Test
    @DisplayName("null 로그인 ID로 LoginId를 생성하면 예외가 발생한다")
    void createLoginIdWithNull() {
        // when & then
        assertThatThrownBy(() -> LoginId.of(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("LoginId cannot be null or empty");
    }

    @Test
    @DisplayName("빈 문자열로 LoginId를 생성하면 예외가 발생한다")
    void createLoginIdWithEmpty() {
        // when & then
        assertThatThrownBy(() -> LoginId.of(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("LoginId cannot be null or empty");
    }

    @Test
    @DisplayName("공백 문자열로 LoginId를 생성하면 예외가 발생한다")
    void createLoginIdWithBlank() {
        // when & then
        assertThatThrownBy(() -> LoginId.of("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("LoginId cannot be null or empty");
    }
}