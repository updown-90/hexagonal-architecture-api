package com.updown.api.department.application.port.in.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

/**
 * 부서 생성 커맨드
 */
@Data
@Builder
public class CreateDepartmentCommand {
    
    @NotEmpty
    private String name;
}
