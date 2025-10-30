package com.updown.api.department.application.port.in.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 부서 응답 DTO
 */
@Data
@Builder
public class DepartmentResponse {
    
    private Long id;
    private String name;
}
