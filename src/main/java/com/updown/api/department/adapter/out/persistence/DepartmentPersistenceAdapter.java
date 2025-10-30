package com.updown.api.department.adapter.out.persistence;

import com.updown.api.department.application.port.out.LoadDepartmentOutPort;
import com.updown.api.department.application.port.out.SaveDepartmentOutPort;
import com.updown.api.department.domain.Department;
import com.updown.api.department.domain.vo.DepartmentId;
import com.updown.api.department.domain.vo.DepartmentName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * 부서 영속성 어댑터
 */
@Component
@RequiredArgsConstructor
public class DepartmentPersistenceAdapter implements LoadDepartmentOutPort, SaveDepartmentOutPort {
    
    private final DepartmentRepository departmentRepository;
    
    @Override
    public Optional<Department> loadDepartment(Long departmentId) {
        return departmentRepository.findById(departmentId)
                .map(this::mapToDomain);
    }
    
    @Override
    public List<Department> loadAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(this::mapToDomain)
                .toList();
    }
    
    @Override
    public Department saveDepartment(Department department) {
        DepartmentJpaEntity jpaEntity = mapToJpaEntity(department);
        DepartmentJpaEntity savedEntity = departmentRepository.save(jpaEntity);
        return mapToDomain(savedEntity);
    }
    
    @Override
    public void deleteDepartment(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }
    
    private Department mapToDomain(DepartmentJpaEntity jpaEntity) {
        return Department.builder()
                .id(DepartmentId.of(jpaEntity.getId()))
                .name(DepartmentName.of(jpaEntity.getName()))
                .build();
    }
    
    private DepartmentJpaEntity mapToJpaEntity(Department department) {
        return DepartmentJpaEntity.builder()
                .id(department.getId() != null ? department.getId().getValue() : null)
                .name(department.getName().getValue())
                .build();
    }
}
