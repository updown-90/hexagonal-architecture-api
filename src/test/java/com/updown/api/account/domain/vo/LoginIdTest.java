package com.updown.api.account.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class LoginIdTest {

    @Test
    @DisplayName("LoginId를 생성할 수 있다")
    void createLoginId() {
        // given
        String value = "testuser";

        // when
        LoginId loginId = LoginId.of(value);

        // then
        assertThat(loginId.getValue()).isEqualTo(value);
    }

    @Test
    @DisplayName("null 값으로 LoginId를 생성하면 예외가 발생한다")
    void createLoginIdWithNull() {
        // when & then
        assertThatThrownBy(() -> LoginId.of(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로그인 ID는 필수입니다");
    }

    @Test
    @DisplayName("빈 문자열로 LoginId를 생성하면 예외가 발생한다")
    void createLoginIdWithEmptyString() {
        // when & then
        assertThatThrownBy(() -> LoginId.of(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로그인 ID는 필수입니다");
    }

    @Test
    @DisplayName("공백만 있는 문자열로 LoginId를 생성하면 예외가 발생한다")
    void createLoginIdWithBlankString() {
        // when & then
        assertThatThrownBy(() -> LoginId.of("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로그인 ID는 필수입니다");
    }

    @Test
    @DisplayName("같은 값을 가진 LoginId는 동등하다")
    void equalsAndHashCode() {
        // given
        String value = "testuser";
        LoginId loginId1 = LoginId.of(value);
        LoginId loginId2 = LoginId.of(value);

        // when & then
        assertThat(loginId1).isEqualTo(loginId2);
        assertThat(loginId1.hashCode()).isEqualTo(loginId2.hashCode());
    }

    @Test
    @DisplayName("다른 값을 가진 LoginId는 동등하지 않다")
    void notEquals() {
        // given
        LoginId loginId1 = LoginId.of("user1");
        LoginId loginId2 = LoginId.of("user2");

        // when & then
        assertThat(loginId1).isNotEqualTo(loginId2);
    }
}
