package com.updown.api.department.application.service;

import com.updown.api.department.application.port.in.dto.DepartmentResponse;
import com.updown.api.department.application.port.out.LoadDepartmentOutPort;
import com.updown.api.department.domain.Department;
import com.updown.api.common.exception.CustomRuntimeException;
import com.updown.api.common.exception.ExceptionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

/**
 * GetDepartmentService 테스트
 */
@ExtendWith(MockitoExtension.class)
class GetDepartmentServiceTest {

    @Mock
    private LoadDepartmentOutPort loadDepartmentOutPort;

    @InjectMocks
    private GetDepartmentService getDepartmentService;

    @Test
    @DisplayName("부서를 조회할 수 있다")
    void getDepartment() {
        // given
        Long departmentId = 1L;
        Department department = Department.builder()
                .id(com.updown.api.department.domain.vo.DepartmentId.of(departmentId))
                .name(com.updown.api.department.domain.vo.DepartmentName.of("개발팀"))
                .build();

        given(loadDepartmentOutPort.loadDepartment(departmentId)).willReturn(Optional.of(department));

        // when
        DepartmentResponse response = getDepartmentService.getDepartment(departmentId);

        // then
        assertThat(response.getId()).isEqualTo(departmentId);
        assertThat(response.getName()).isEqualTo("개발팀");
    }

    @Test
    @DisplayName("존재하지 않는 부서 조회 시 예외가 발생한다")
    void getNonExistentDepartment() {
        // given
        Long departmentId = 999L;
        given(loadDepartmentOutPort.loadDepartment(departmentId)).willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> getDepartmentService.getDepartment(departmentId))
                .isInstanceOf(CustomRuntimeException.class)
                .hasFieldOrPropertyWithValue("exceptionType", ExceptionType.NOT_FOUND_DEPARTMENT);
    }
}
