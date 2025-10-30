package com.updown.api.account.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class AccountIdTest {

    @Test
    @DisplayName("AccountId를 생성할 수 있다")
    void createAccountId() {
        // given
        Long value = 1L;

        // when
        AccountId accountId = AccountId.of(value);

        // then
        assertThat(accountId.getValue()).isEqualTo(value);
    }

    @Test
    @DisplayName("null 값으로 AccountId를 생성하면 예외가 발생한다")
    void createAccountIdWithNull() {
        // when & then
        assertThatThrownBy(() -> AccountId.of(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("AccountId는 양수여야 합니다");
    }

    @Test
    @DisplayName("0 이하의 값으로 AccountId를 생성하면 예외가 발생한다")
    void createAccountIdWithZero() {
        // when & then
        assertThatThrownBy(() -> AccountId.of(0L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("AccountId는 양수여야 합니다");
    }

    @Test
    @DisplayName("음수 값으로 AccountId를 생성하면 예외가 발생한다")
    void createAccountIdWithNegative() {
        // when & then
        assertThatThrownBy(() -> AccountId.of(-1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("AccountId는 양수여야 합니다");
    }

    @Test
    @DisplayName("양수 값으로 AccountId를 생성할 수 있다")
    void createAccountIdWithPositive() {
        // given
        Long value = 100L;

        // when
        AccountId accountId = AccountId.of(value);

        // then
        assertThat(accountId.getValue()).isEqualTo(value);
    }

    @Test
    @DisplayName("같은 값을 가진 AccountId는 동등하다")
    void equalsAndHashCode() {
        // given
        Long value = 1L;
        AccountId accountId1 = AccountId.of(value);
        AccountId accountId2 = AccountId.of(value);

        // when & then
        assertThat(accountId1).isEqualTo(accountId2);
        assertThat(accountId1.hashCode()).isEqualTo(accountId2.hashCode());
    }

    @Test
    @DisplayName("다른 값을 가진 AccountId는 동등하지 않다")
    void notEquals() {
        // given
        AccountId accountId1 = AccountId.of(1L);
        AccountId accountId2 = AccountId.of(2L);

        // when & then
        assertThat(accountId1).isNotEqualTo(accountId2);
    }
}
