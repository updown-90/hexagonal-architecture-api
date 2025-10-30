package com.updown.api.department.application.port.in;

/**
 * 부서 삭제 인바운드 포트
 */
public interface DeleteDepartmentInPort {
    
    void deleteDepartment(Long departmentId);
}
