package com.updown.api.department.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DepartmentFindResponseDTO {

    private Long id;

    private String name;

}
