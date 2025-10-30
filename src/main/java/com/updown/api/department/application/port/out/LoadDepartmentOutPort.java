package com.updown.api.department.application.port.out;

import com.updown.api.department.domain.Department;

import java.util.List;
import java.util.Optional;

/**
 * 부서 조회 아웃바운드 포트
 */
public interface LoadDepartmentOutPort {
    
    Optional<Department> loadDepartment(Long departmentId);
    
    List<Department> loadAllDepartments();
}
