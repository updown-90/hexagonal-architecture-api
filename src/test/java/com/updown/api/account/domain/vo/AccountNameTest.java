package com.updown.api.account.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class AccountNameTest {

    @Test
    @DisplayName("AccountName을 생성할 수 있다")
    void createAccountName() {
        // given
        String value = "홍길동";

        // when
        AccountName accountName = AccountName.of(value);

        // then
        assertThat(accountName.getValue()).isEqualTo(value);
    }

    @Test
    @DisplayName("null 값으로 AccountName을 생성하면 예외가 발생한다")
    void createAccountNameWithNull() {
        // when & then
        assertThatThrownBy(() -> AccountName.of(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("계정명은 필수입니다");
    }

    @Test
    @DisplayName("빈 문자열로 AccountName을 생성하면 예외가 발생한다")
    void createAccountNameWithEmptyString() {
        // when & then
        assertThatThrownBy(() -> AccountName.of(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("계정명은 필수입니다");
    }

    @Test
    @DisplayName("공백만 있는 문자열로 AccountName을 생성하면 예외가 발생한다")
    void createAccountNameWithBlankString() {
        // when & then
        assertThatThrownBy(() -> AccountName.of("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("계정명은 필수입니다");
    }

    @Test
    @DisplayName("한글 계정명을 생성할 수 있다")
    void createAccountNameWithKorean() {
        // given
        String value = "김철수";

        // when
        AccountName accountName = AccountName.of(value);

        // then
        assertThat(accountName.getValue()).isEqualTo(value);
    }

    @Test
    @DisplayName("영문 계정명을 생성할 수 있다")
    void createAccountNameWithEnglish() {
        // given
        String value = "John Doe";

        // when
        AccountName accountName = AccountName.of(value);

        // then
        assertThat(accountName.getValue()).isEqualTo(value);
    }

    @Test
    @DisplayName("같은 값을 가진 AccountName은 동등하다")
    void equalsAndHashCode() {
        // given
        String value = "홍길동";
        AccountName accountName1 = AccountName.of(value);
        AccountName accountName2 = AccountName.of(value);

        // when & then
        assertThat(accountName1).isEqualTo(accountName2);
        assertThat(accountName1.hashCode()).isEqualTo(accountName2.hashCode());
    }

    @Test
    @DisplayName("다른 값을 가진 AccountName은 동등하지 않다")
    void notEquals() {
        // given
        AccountName accountName1 = AccountName.of("홍길동");
        AccountName accountName2 = AccountName.of("김철수");

        // when & then
        assertThat(accountName1).isNotEqualTo(accountName2);
    }
}
