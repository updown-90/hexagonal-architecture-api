package com.updown.api.department.service;

import com.updown.api.department.domain.DepartmentEntity;
import com.updown.api.department.presentation.dto.request.DepartmentSaveRequest;
import com.updown.api.department.presentation.dto.request.DepartmentUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public List<DepartmentEntity> findAll() {
        return departmentRepository.findAll();
    }

    public DepartmentEntity createDepartment(DepartmentSaveRequest departmentSaveRequest) {
        return departmentRepository.save(
                DepartmentEntity.create(departmentSaveRequest)
        );
    }

    public DepartmentEntity findById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("부서를 찾을 수 없습니다: " + id));
    }

    public DepartmentEntity updateDepartment(Long id, DepartmentUpdateRequest departmentUpdateRequest) {
        DepartmentEntity department = findById(id);
        department.update(departmentUpdateRequest);
        return departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        DepartmentEntity department = findById(id);
        departmentRepository.delete(department);
    }
}
