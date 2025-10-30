package com.updown.api.department.application.service;

import com.updown.api.department.application.port.in.GetDepartmentInPort;
import com.updown.api.department.application.port.in.dto.DepartmentResponse;
import com.updown.api.department.application.port.out.LoadDepartmentOutPort;
import com.updown.api.department.domain.Department;
import com.updown.api.common.exception.CustomRuntimeException;
import com.updown.api.common.exception.ExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 부서 조회 서비스
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetDepartmentService implements GetDepartmentInPort {
    
    private final LoadDepartmentOutPort loadDepartmentOutPort;
    
    @Override
    public DepartmentResponse getDepartment(Long departmentId) {
        Department department = loadDepartmentOutPort.loadDepartment(departmentId)
                .orElseThrow(() -> new CustomRuntimeException(ExceptionType.NOT_FOUND_DEPARTMENT));
        
        return DepartmentResponse.builder()
                .id(department.getId().getValue())
                .name(department.getName().getValue())
                .build();
    }
}
