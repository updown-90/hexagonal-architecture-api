package com.updown.api.department.application.service;

import com.updown.api.department.application.port.in.UpdateDepartmentInPort;
import com.updown.api.department.application.port.in.dto.UpdateDepartmentCommand;
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
 * 부서 수정 서비스
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UpdateDepartmentService implements UpdateDepartmentInPort {
    
    private final LoadDepartmentOutPort loadDepartmentOutPort;
    private final SaveDepartmentOutPort saveDepartmentOutPort;
    
    @Override
    public DepartmentResponse updateDepartment(UpdateDepartmentCommand command) {
        Department department = loadDepartmentOutPort.loadDepartment(command.getDepartmentId())
                .orElseThrow(() -> new CustomRuntimeException(ExceptionType.NOT_FOUND_DEPARTMENT));
        
        department.update(command);
        Department savedDepartment = saveDepartmentOutPort.saveDepartment(department);
        
        return DepartmentResponse.builder()
                .id(savedDepartment.getId().getValue())
                .name(savedDepartment.getName().getValue())
                .build();
    }
}
