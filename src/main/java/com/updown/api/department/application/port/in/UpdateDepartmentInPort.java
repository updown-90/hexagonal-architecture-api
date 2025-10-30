package com.updown.api.department.application.port.in;

import com.updown.api.department.application.port.in.dto.UpdateDepartmentCommand;
import com.updown.api.department.application.port.in.dto.DepartmentResponse;

/**
 * 부서 수정 인바운드 포트
 */
public interface UpdateDepartmentInPort {
    
    DepartmentResponse updateDepartment(UpdateDepartmentCommand command);
}
