package com.updown.api.department.application.port.in;

import com.updown.api.department.application.port.in.dto.CreateDepartmentCommand;
import com.updown.api.department.application.port.in.dto.DepartmentResponse;

/**
 * 부서 생성 인바운드 포트
 */
public interface CreateDepartmentInPort {
    
    DepartmentResponse createDepartment(CreateDepartmentCommand command);
}
