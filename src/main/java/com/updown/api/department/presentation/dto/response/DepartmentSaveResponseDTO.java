package com.updown.api.department.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DepartmentSaveResponseDTO {

    private Long id;

    private String name;

}
