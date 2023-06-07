package com.updown.api.department.presentation.mapstruct.mapper;


import com.updown.api.department.domain.DepartmentEntity;
import com.updown.api.department.presentation.dto.response.DepartmentFindResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DepartmentEntityMapper {

    DepartmentEntityMapper INSTANCE = Mappers.getMapper(DepartmentEntityMapper.class);

    DepartmentFindResponseDTO departmentEntityToDepartmentFindResponseDTO(DepartmentEntity departmentEntity);
    List<DepartmentFindResponseDTO> departmentEntityToDepartmentFindResponseDTO(List<DepartmentEntity> departmentEntity);

}
