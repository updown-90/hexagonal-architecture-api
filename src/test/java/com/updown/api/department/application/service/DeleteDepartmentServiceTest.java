package com.updown.api.department.application.service;

import com.updown.api.department.application.port.out.LoadDepartmentOutPort;
import com.updown.api.department.application.port.out.SaveDepartmentOutPort;
import com.updown.api.common.exception.CustomRuntimeException;
import com.updown.api.common.exception.ExceptionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * DeleteDepartmentService 테스트
 */
@ExtendWith(MockitoExtension.class)
class DeleteDepartmentServiceTest {

    @Mock
    private LoadDepartmentOutPort loadDepartmentOutPort;

    @Mock
    private SaveDepartmentOutPort saveDepartmentOutPort;

    @InjectMocks
    private DeleteDepartmentService deleteDepartmentService;

    @Test
    @DisplayName("부서를 성공적으로 삭제할 수 있다")
    void deleteDepartmentSuccess() {
        // given
        Long departmentId = 1L;
        given(loadDepartmentOutPort.loadDepartment(departmentId)).willReturn(Optional.of(
                com.updown.api.department.domain.Department.builder()
                        .id(com.updown.api.department.domain.vo.DepartmentId.of(departmentId))
                        .name(com.updown.api.department.domain.vo.DepartmentName.of("개발팀"))
                        .build()
        ));

        // when
        deleteDepartmentService.deleteDepartment(departmentId);

        // then
        then(loadDepartmentOutPort).should().loadDepartment(departmentId);
        then(saveDepartmentOutPort).should().deleteDepartment(departmentId);
    }

    @Test
    @DisplayName("존재하지 않는 부서 삭제 시 예외가 발생한다")
    void deleteNonExistentDepartment() {
        // given
        Long departmentId = 999L;
        given(loadDepartmentOutPort.loadDepartment(departmentId)).willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> deleteDepartmentService.deleteDepartment(departmentId))
                .isInstanceOf(CustomRuntimeException.class)
                .hasFieldOrPropertyWithValue("exceptionType", ExceptionType.NOT_FOUND_DEPARTMENT);
    }
}
