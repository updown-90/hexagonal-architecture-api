package com.updown.api.account.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * AccountId 값 객체 테스트
 */
class AccountIdTest {

    @Test
    @DisplayName("유효한 ID로 AccountId를 생성할 수 있다")
    void createValidAccountId() {
        // given
        Long validId = 1L;

        // when
        AccountId accountId = AccountId.of(validId);

        // then
        assertThat(accountId.getValue()).isEqualTo(validId);
    }

    @Test
    @DisplayName("null ID로 AccountId를 생성하면 예외가 발생한다")
    void createAccountIdWithNull() {
        // when & then
        assertThatThrownBy(() -> AccountId.of(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("AccountId must be positive");
    }

    @Test
    @DisplayName("음수 ID로 AccountId를 생성하면 예외가 발생한다")
    void createAccountIdWithNegative() {
        // when & then
        assertThatThrownBy(() -> AccountId.of(-1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("AccountId must be positive");
    }

    @Test
    @DisplayName("0 ID로 AccountId를 생성하면 예외가 발생한다")
    void createAccountIdWithZero() {
        // when & then
        assertThatThrownBy(() -> AccountId.of(0L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("AccountId must be positive");
    }
}