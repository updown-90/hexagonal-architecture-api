package com.updown.api.department.service;

import com.updown.api.department.domain.DepartmentEntity;
import com.updown.api.department.presentation.dto.request.DepartmentSaveRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public List<DepartmentEntity> findAll() {
        List<DepartmentEntity> all = departmentRepository.findAll();

        return all;
    }

    public DepartmentEntity createDepartment(DepartmentSaveRequestDTO departmentSaveRequestDTO) {
        return departmentRepository.save(
                DepartmentEntity.create(departmentSaveRequestDTO)
        );
    }
}
