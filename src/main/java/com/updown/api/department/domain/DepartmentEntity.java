package com.updown.api.department.domain;

import com.updown.api.account.domain.AccountEntity;
import com.updown.api.common.domain.BaseTimeEntity;
import com.updown.api.department.presentation.dto.request.DepartmentSaveRequest;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "department")
public class DepartmentEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    private List<AccountEntity> accounts;

    @Builder
    public DepartmentEntity(String name) {
        this.name = name;
    }

    public static DepartmentEntity create(DepartmentSaveRequest departmentSaveRequest) {
        return DepartmentEntity.builder()
                .name(departmentSaveRequest.getName())
                .build();
    }
}
