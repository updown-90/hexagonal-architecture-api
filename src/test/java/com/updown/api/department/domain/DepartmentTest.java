package com.updown.api.department.domain;

import com.updown.api.department.application.port.in.dto.CreateDepartmentCommand;
import com.updown.api.department.application.port.in.dto.UpdateDepartmentCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Department 도메인 테스트
 */
class DepartmentTest {

    @Test
    @DisplayName("부서를 생성할 수 있다")
    void createDepartment() {
        // given
        CreateDepartmentCommand command = CreateDepartmentCommand.builder()
                .name("개발팀")
                .build();

        // when
        Department department = Department.create(command);

        // then
        assertThat(department.getName().getValue()).isEqualTo("개발팀");
    }

    @Test
    @DisplayName("부서 정보를 수정할 수 있다")
    void updateDepartment() {
        // given
        Department department = Department.builder()
                .id(com.updown.api.department.domain.vo.DepartmentId.of(1L))
                .name(com.updown.api.department.domain.vo.DepartmentName.of("기존부서"))
                .build();

        UpdateDepartmentCommand command = UpdateDepartmentCommand.builder()
                .departmentId(1L)
                .name("수정된부서")
                .build();

        // when
        department.update(command);

        // then
        assertThat(department.getName().getValue()).isEqualTo("수정된부서");
    }

    @Test
    @DisplayName("ID와 이름이 있는 부서를 생성할 수 있다")
    void createDepartmentWithIdAndName() {
        // given
        Long departmentId = 1L;
        String departmentName = "마케팅팀";

        // when
        Department department = Department.builder()
                .id(com.updown.api.department.domain.vo.DepartmentId.of(departmentId))
                .name(com.updown.api.department.domain.vo.DepartmentName.of(departmentName))
                .build();

        // then
        assertThat(department.getId().getValue()).isEqualTo(departmentId);
        assertThat(department.getName().getValue()).isEqualTo(departmentName);
    }
}
