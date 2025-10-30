package com.updown.api.department.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * DepartmentId 값 객체 테스트
 */
class DepartmentIdTest {

    @Test
    @DisplayName("유효한 ID로 DepartmentId를 생성할 수 있다")
    void createValidDepartmentId() {
        // given
        Long validId = 1L;

        // when
        DepartmentId departmentId = DepartmentId.of(validId);

        // then
        assertThat(departmentId.getValue()).isEqualTo(validId);
    }

    @Test
    @DisplayName("null ID로 DepartmentId를 생성하면 예외가 발생한다")
    void createDepartmentIdWithNull() {
        // when & then
        assertThatThrownBy(() -> DepartmentId.of(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("DepartmentId must be positive");
    }

    @Test
    @DisplayName("음수 ID로 DepartmentId를 생성하면 예외가 발생한다")
    void createDepartmentIdWithNegative() {
        // when & then
        assertThatThrownBy(() -> DepartmentId.of(-1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("DepartmentId must be positive");
    }

    @Test
    @DisplayName("0 ID로 DepartmentId를 생성하면 예외가 발생한다")
    void createDepartmentIdWithZero() {
        // when & then
        assertThatThrownBy(() -> DepartmentId.of(0L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("DepartmentId must be positive");
    }

    @Test
    @DisplayName("동일한 ID로 생성된 DepartmentId 객체는 같다")
    void departmentIdEquality() {
        // given
        Long idValue = 1L;
        DepartmentId departmentId1 = DepartmentId.of(idValue);
        DepartmentId departmentId2 = DepartmentId.of(idValue);

        // when & then
        assertThat(departmentId1).isEqualTo(departmentId2);
        assertThat(departmentId1.hashCode()).isEqualTo(departmentId2.hashCode());
    }

    @Test
    @DisplayName("다른 ID로 생성된 DepartmentId 객체는 다르다")
    void departmentIdInequality() {
        // given
        DepartmentId departmentId1 = DepartmentId.of(1L);
        DepartmentId departmentId2 = DepartmentId.of(2L);

        // when & then
        assertThat(departmentId1).isNotEqualTo(departmentId2);
    }
}
