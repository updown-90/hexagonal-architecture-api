package com.updown.api.department.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;


@Getter
public class DepartmentSaveRequest {

    @NotEmpty
    private String name;

}
