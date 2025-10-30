package com.updown.api.department.application.service;

import com.updown.api.department.application.port.in.dto.UpdateDepartmentCommand;
import com.updown.api.department.application.port.in.dto.DepartmentResponse;
import com.updown.api.department.application.port.out.LoadDepartmentOutPort;
import com.updown.api.department.application.port.out.SaveDepartmentOutPort;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * UpdateDepartmentService 테스트
 */
@ExtendWith(MockitoExtension.class)
class UpdateDepartmentServiceTest {

    @Mock
    private LoadDepartmentOutPort loadDepartmentOutPort;

    @Mock
    private SaveDepartmentOutPort saveDepartmentOutPort;

    @InjectMocks
    private UpdateDepartmentService updateDepartmentService;

    @Test
    @DisplayName("부서를 성공적으로 수정할 수 있다")
    void updateDepartmentSuccess() {
        // given
        UpdateDepartmentCommand command = UpdateDepartmentCommand.builder()
                .departmentId(1L)
                .name("수정된부서")
                .build();

        Department department = Department.builder()
                .id(com.updown.api.department.domain.vo.DepartmentId.of(1L))
                .name(com.updown.api.department.domain.vo.DepartmentName.of("기존부서"))
                .build();

        Department savedDepartment = Department.builder()
                .id(com.updown.api.department.domain.vo.DepartmentId.of(1L))
                .name(com.updown.api.department.domain.vo.DepartmentName.of("수정된부서"))
                .build();

        given(loadDepartmentOutPort.loadDepartment(1L)).willReturn(Optional.of(department));
        given(saveDepartmentOutPort.saveDepartment(any(Department.class))).willReturn(savedDepartment);

        // when
        DepartmentResponse response = updateDepartmentService.updateDepartment(command);

        // then
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getName()).isEqualTo("수정된부서");

        then(loadDepartmentOutPort).should().loadDepartment(1L);
        then(saveDepartmentOutPort).should().saveDepartment(any(Department.class));
    }

    @Test
    @DisplayName("존재하지 않는 부서 수정 시 예외가 발생한다")
    void updateNonExistentDepartment() {
        // given
        UpdateDepartmentCommand command = UpdateDepartmentCommand.builder()
                .departmentId(999L)
                .name("수정된부서")
                .build();

        given(loadDepartmentOutPort.loadDepartment(999L)).willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> updateDepartmentService.updateDepartment(command))
                .isInstanceOf(CustomRuntimeException.class)
                .hasFieldOrPropertyWithValue("exceptionType", ExceptionType.NOT_FOUND_DEPARTMENT);
    }
}
