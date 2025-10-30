package com.updown.api.department.application.service;

import com.updown.api.department.application.port.in.dto.CreateDepartmentCommand;
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

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * CreateDepartmentService 테스트
 */
@ExtendWith(MockitoExtension.class)
class CreateDepartmentServiceTest {

    @Mock
    private LoadDepartmentOutPort loadDepartmentOutPort;

    @Mock
    private SaveDepartmentOutPort saveDepartmentOutPort;

    @InjectMocks
    private CreateDepartmentService createDepartmentService;

    @Test
    @DisplayName("부서를 성공적으로 생성할 수 있다")
    void createDepartmentSuccess() {
        // given
        CreateDepartmentCommand command = CreateDepartmentCommand.builder()
                .name("개발팀")
                .build();

        Department savedDepartment = Department.builder()
                .id(com.updown.api.department.domain.vo.DepartmentId.of(1L))
                .name(com.updown.api.department.domain.vo.DepartmentName.of("개발팀"))
                .build();

        given(loadDepartmentOutPort.loadAllDepartments()).willReturn(Collections.emptyList());
        given(saveDepartmentOutPort.saveDepartment(any(Department.class))).willReturn(savedDepartment);

        // when
        DepartmentResponse response = createDepartmentService.createDepartment(command);

        // then
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getName()).isEqualTo("개발팀");

        then(loadDepartmentOutPort).should().loadAllDepartments();
        then(saveDepartmentOutPort).should().saveDepartment(any(Department.class));
    }

    @Test
    @DisplayName("중복된 부서명으로 부서 생성 시 예외가 발생한다")
    void createDepartmentWithDuplicateName() {
        // given
        CreateDepartmentCommand command = CreateDepartmentCommand.builder()
                .name("개발팀")
                .build();

        Department existingDepartment = Department.builder()
                .id(com.updown.api.department.domain.vo.DepartmentId.of(1L))
                .name(com.updown.api.department.domain.vo.DepartmentName.of("개발팀"))
                .build();

        given(loadDepartmentOutPort.loadAllDepartments()).willReturn(Collections.singletonList(existingDepartment));

        // when & then
        assertThatThrownBy(() -> createDepartmentService.createDepartment(command))
                .isInstanceOf(CustomRuntimeException.class)
                .hasFieldOrPropertyWithValue("exceptionType", ExceptionType.DUPLICATE_DEPARTMENT);
    }
}
