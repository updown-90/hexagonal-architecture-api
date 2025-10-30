package com.updown.api.department.application.service;

import com.updown.api.department.application.port.in.CreateDepartmentInPort;
import com.updown.api.department.application.port.in.dto.CreateDepartmentCommand;
import com.updown.api.department.application.port.in.dto.DepartmentResponse;
import com.updown.api.department.application.port.out.LoadDepartmentOutPort;
import com.updown.api.department.application.port.out.SaveDepartmentOutPort;
import com.updown.api.department.domain.Department;
import com.updown.api.common.exception.CustomRuntimeException;
import com.updown.api.common.exception.ExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 부서 생성 서비스
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CreateDepartmentService implements CreateDepartmentInPort {
    
    private final LoadDepartmentOutPort loadDepartmentOutPort;
    private final SaveDepartmentOutPort saveDepartmentOutPort;
    
    @Override
    public DepartmentResponse createDepartment(CreateDepartmentCommand command) {
        // 부서명 중복 확인
        if (loadDepartmentOutPort.loadAllDepartments().stream()
                .anyMatch(dept -> dept.getName().getValue().equals(command.getName()))) {
            throw new CustomRuntimeException(ExceptionType.DUPLICATE_DEPARTMENT);
        }
        
        // 부서 생성
        Department department = Department.create(command);
        Department savedDepartment = saveDepartmentOutPort.saveDepartment(department);
        
        return DepartmentResponse.builder()
                .id(savedDepartment.getId().getValue())
                .name(savedDepartment.getName().getValue())
                .build();
    }
}
