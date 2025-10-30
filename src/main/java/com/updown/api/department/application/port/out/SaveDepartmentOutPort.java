package com.updown.api.department.application.port.out;

import com.updown.api.department.domain.Department;

/**
 * 부서 저장 아웃바운드 포트
 */
public interface SaveDepartmentOutPort {
    
    Department saveDepartment(Department department);
    
    void deleteDepartment(Long departmentId);
}
