package com.updown.api.department.service;

import com.updown.api.department.domain.DepartmentEntity;
import com.updown.api.department.presentation.dto.request.DepartmentSaveRequest;
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
}
