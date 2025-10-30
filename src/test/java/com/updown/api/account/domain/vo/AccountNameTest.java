package com.updown.api.account.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * AccountName 값 객체 테스트
 */
class AccountNameTest {

    @Test
    @DisplayName("유효한 계정명으로 AccountName을 생성할 수 있다")
    void createValidAccountName() {
        // given
        String validName = "테스트사용자";

        // when
        AccountName accountName = AccountName.of(validName);

        // then
        assertThat(accountName.getValue()).isEqualTo(validName);
    }

    @Test
    @DisplayName("영문 계정명을 생성할 수 있다")
    void createEnglishAccountName() {
        // given
        String englishName = "testuser";

        // when
        AccountName accountName = AccountName.of(englishName);

        // then
        assertThat(accountName.getValue()).isEqualTo(englishName);
    }

    @Test
    @DisplayName("한글 계정명을 생성할 수 있다")
    void createKoreanAccountName() {
        // given
        String koreanName = "홍길동";

        // when
        AccountName accountName = AccountName.of(koreanName);

        // then
        assertThat(accountName.getValue()).isEqualTo(koreanName);
    }

    @Test
    @DisplayName("null 값으로 AccountName을 생성하면 예외가 발생한다")
    void createAccountNameWithNull() {
        // when & then
        assertThatThrownBy(() -> AccountName.of(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("AccountName cannot be null or empty");
    }

    @Test
    @DisplayName("빈 문자열로 AccountName을 생성하면 예외가 발생한다")
    void createAccountNameWithEmpty() {
        // when & then
        assertThatThrownBy(() -> AccountName.of(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("AccountName cannot be null or empty");
    }

    @Test
    @DisplayName("공백만 있는 문자열로 AccountName을 생성하면 예외가 발생한다")
    void createAccountNameWithBlank() {
        // when & then
        assertThatThrownBy(() -> AccountName.of("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("AccountName cannot be null or empty");
    }

    @Test
    @DisplayName("같은 값을 가진 AccountName은 동등하다")
    void accountNameEquality() {
        // given
        String nameValue = "테스트사용자";
        AccountName accountName1 = AccountName.of(nameValue);
        AccountName accountName2 = AccountName.of(nameValue);

        // when & then
        assertThat(accountName1).isEqualTo(accountName2);
        assertThat(accountName1.hashCode()).isEqualTo(accountName2.hashCode());
    }

    @Test
    @DisplayName("다른 값을 가진 AccountName은 동등하지 않다")
    void accountNameInequality() {
        // given
        AccountName accountName1 = AccountName.of("사용자1");
        AccountName accountName2 = AccountName.of("사용자2");

        // when & then
        assertThat(accountName1).isNotEqualTo(accountName2);
    }
}