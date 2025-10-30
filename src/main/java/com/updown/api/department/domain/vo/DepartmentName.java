package com.updown.api.department.domain.vo;

import lombok.Value;

/**
 * 부서명 값 객체
 */
@Value
public class DepartmentName {
    String value;
    
    public static DepartmentName of(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("DepartmentName cannot be null or empty");
        }
        return new DepartmentName(value);
    }
}
