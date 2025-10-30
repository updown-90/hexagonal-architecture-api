package com.updown.api.department.domain;

import com.updown.api.department.domain.vo.DepartmentId;
import com.updown.api.department.domain.vo.DepartmentName;
import com.updown.api.department.application.port.in.dto.CreateDepartmentCommand;
import com.updown.api.department.application.port.in.dto.UpdateDepartmentCommand;
import lombok.Builder;
import lombok.Getter;

/**
 * 부서 도메인 엔티티
 */
@Getter
public class Department {
    
    private final DepartmentId id;
    private DepartmentName name;
    
    @Builder
    public Department(DepartmentId id, DepartmentName name) {
        this.id = id;
        this.name = name;
    }
    
    /**
     * 부서 생성 팩토리 메서드
     */
    public static Department create(CreateDepartmentCommand command) {
        return Department.builder()
                .name(DepartmentName.of(command.getName()))
                .build();
    }
    
    /**
     * 부서 정보 수정
     */
    public void update(UpdateDepartmentCommand command) {
        this.name = DepartmentName.of(command.getName());
    }
}
