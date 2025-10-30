package com.updown.api.department.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 부서 JPA 리포지토리
 */
public interface DepartmentRepository extends JpaRepository<DepartmentJpaEntity, Long> {
    
    Optional<DepartmentJpaEntity> findByName(String name);
}
