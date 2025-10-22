package com.updown.api.department.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
public class DepartmentUpdateRequest {

    @NotEmpty
    private String name;

}