package com.updown.api.department.application.port.in;

import com.updown.api.department.application.port.in.dto.DepartmentResponse;

/**
 * 부서 조회 인바운드 포트
 */
public interface GetDepartmentInPort {
    
    DepartmentResponse getDepartment(Long departmentId);
}
