package com.updown.api.department.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * DepartmentName 값 객체 테스트
 */
class DepartmentNameTest {

    @Test
    @DisplayName("유효한 부서명으로 DepartmentName을 생성할 수 있다")
    void createValidDepartmentName() {
        // given
        String validName = "개발팀";

        // when
        DepartmentName departmentName = DepartmentName.of(validName);

        // then
        assertThat(departmentName.getValue()).isEqualTo(validName);
    }

    @Test
    @DisplayName("영문 부서명을 생성할 수 있다")
    void createEnglishDepartmentName() {
        // given
        String englishName = "Development Team";

        // when
        DepartmentName departmentName = DepartmentName.of(englishName);

        // then
        assertThat(departmentName.getValue()).isEqualTo(englishName);
    }

    @Test
    @DisplayName("한글 부서명을 생성할 수 있다")
    void createKoreanDepartmentName() {
        // given
        String koreanName = "마케팅팀";

        // when
        DepartmentName departmentName = DepartmentName.of(koreanName);

        // then
        assertThat(departmentName.getValue()).isEqualTo(koreanName);
    }

    @Test
    @DisplayName("null 값으로 DepartmentName을 생성하면 예외가 발생한다")
    void createDepartmentNameWithNull() {
        // when & then
        assertThatThrownBy(() -> DepartmentName.of(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("DepartmentName cannot be null or empty");
    }

    @Test
    @DisplayName("빈 문자열로 DepartmentName을 생성하면 예외가 발생한다")
    void createDepartmentNameWithEmpty() {
        // when & then
        assertThatThrownBy(() -> DepartmentName.of(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("DepartmentName cannot be null or empty");
    }

    @Test
    @DisplayName("공백만 있는 문자열로 DepartmentName을 생성하면 예외가 발생한다")
    void createDepartmentNameWithBlank() {
        // when & then
        assertThatThrownBy(() -> DepartmentName.of("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("DepartmentName cannot be null or empty");
    }

    @Test
    @DisplayName("같은 값을 가진 DepartmentName은 동등하다")
    void departmentNameEquality() {
        // given
        String nameValue = "개발팀";
        DepartmentName departmentName1 = DepartmentName.of(nameValue);
        DepartmentName departmentName2 = DepartmentName.of(nameValue);

        // when & then
        assertThat(departmentName1).isEqualTo(departmentName2);
        assertThat(departmentName1.hashCode()).isEqualTo(departmentName2.hashCode());
    }

    @Test
    @DisplayName("다른 값을 가진 DepartmentName은 동등하지 않다")
    void departmentNameInequality() {
        // given
        DepartmentName departmentName1 = DepartmentName.of("개발팀");
        DepartmentName departmentName2 = DepartmentName.of("마케팅팀");

        // when & then
        assertThat(departmentName1).isNotEqualTo(departmentName2);
    }
}
