package com.updown.api.department.application.port.in.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

/**
 * 부서 수정 커맨드
 */
@Data
@Builder
public class UpdateDepartmentCommand {
    
    @NotNull
    private Long departmentId;
    
    @NotEmpty
    private String name;
}
