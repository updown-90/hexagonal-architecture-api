package com.updown.api.department.domain.vo;

import lombok.Value;

/**
 * 부서 ID 값 객체
 */
@Value
public class DepartmentId {
    Long value;
    
    public static DepartmentId of(Long value) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("DepartmentId must be positive");
        }
        return new DepartmentId(value);
    }
}
