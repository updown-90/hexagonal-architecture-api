package com.updown.api.department.presentation.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class DepartmentSaveRequest {

    @NotEmpty
    private String name;

}
