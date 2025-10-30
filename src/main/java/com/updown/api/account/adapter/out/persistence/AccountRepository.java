package com.updown.api.account.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 계정 JPA 리포지토리
 */
public interface AccountRepository extends JpaRepository<AccountJpaEntity, Long> {
    
    Optional<AccountJpaEntity> findByLoginId(String loginId);
}
