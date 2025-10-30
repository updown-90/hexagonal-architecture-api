package com.updown.api.account.application.port.out;

import com.updown.api.department.domain.Department;

import java.util.Optional;

/**
 * 부서 조회 아웃바운드 포트 (Account에서 사용)
 */
public interface LoadDepartmentOutPort {
    
    Optional<Department> loadDepartment(Long departmentId);
}