package com.updown.api.department.adapter.out.persistence;

import com.updown.api.department.domain.Department;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * DepartmentPersistenceAdapter 테스트
 */
@ExtendWith(MockitoExtension.class)
class DepartmentPersistenceAdapterTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentPersistenceAdapter departmentPersistenceAdapter;

    @Test
    @DisplayName("ID로 부서를 조회할 수 있다")
    void loadDepartmentById() {
        // given
        Long departmentId = 1L;
        DepartmentJpaEntity jpaEntity = DepartmentJpaEntity.builder()
                .id(departmentId)
                .name("개발팀")
                .build();

        given(departmentRepository.findById(departmentId)).willReturn(Optional.of(jpaEntity));

        // when
        Optional<Department> result = departmentPersistenceAdapter.loadDepartment(departmentId);

        // then
        assertThat(result).isPresent();
        Department department = result.get();
        assertThat(department.getId().getValue()).isEqualTo(departmentId);
        assertThat(department.getName().getValue()).isEqualTo("개발팀");

        then(departmentRepository).should().findById(departmentId);
    }

    @Test
    @DisplayName("모든 부서를 조회할 수 있다")
    void loadAllDepartments() {
        // given
        DepartmentJpaEntity jpaEntity1 = DepartmentJpaEntity.builder()
                .id(1L)
                .name("개발팀")
                .build();

        DepartmentJpaEntity jpaEntity2 = DepartmentJpaEntity.builder()
                .id(2L)
                .name("마케팅팀")
                .build();

        given(departmentRepository.findAll()).willReturn(List.of(jpaEntity1, jpaEntity2));

        // when
        List<Department> result = departmentPersistenceAdapter.loadAllDepartments();

        // then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getId().getValue()).isEqualTo(1L);
        assertThat(result.get(0).getName().getValue()).isEqualTo("개발팀");
        assertThat(result.get(1).getId().getValue()).isEqualTo(2L);
        assertThat(result.get(1).getName().getValue()).isEqualTo("마케팅팀");

        then(departmentRepository).should().findAll();
    }

    @Test
    @DisplayName("부서를 저장할 수 있다")
    void saveDepartment() {
        // given
        Department department = Department.builder()
                .name(com.updown.api.department.domain.vo.DepartmentName.of("개발팀"))
                .build();

        DepartmentJpaEntity savedJpaEntity = DepartmentJpaEntity.builder()
                .id(1L)
                .name("개발팀")
                .build();

        given(departmentRepository.save(any(DepartmentJpaEntity.class))).willReturn(savedJpaEntity);

        // when
        Department result = departmentPersistenceAdapter.saveDepartment(department);

        // then
        assertThat(result.getId().getValue()).isEqualTo(1L);
        assertThat(result.getName().getValue()).isEqualTo("개발팀");

        then(departmentRepository).should().save(any(DepartmentJpaEntity.class));
    }

    @Test
    @DisplayName("부서를 삭제할 수 있다")
    void deleteDepartment() {
        // given
        Long departmentId = 1L;

        // when
        departmentPersistenceAdapter.deleteDepartment(departmentId);

        // then
        then(departmentRepository).should().deleteById(departmentId);
    }

    @Test
    @DisplayName("존재하지 않는 부서 조회 시 빈 Optional을 반환한다")
    void loadNonExistentDepartment() {
        // given
        Long departmentId = 999L;
        given(departmentRepository.findById(departmentId)).willReturn(Optional.empty());

        // when
        Optional<Department> result = departmentPersistenceAdapter.loadDepartment(departmentId);

        // then
        assertThat(result).isEmpty();

        then(departmentRepository).should().findById(departmentId);
    }
}
