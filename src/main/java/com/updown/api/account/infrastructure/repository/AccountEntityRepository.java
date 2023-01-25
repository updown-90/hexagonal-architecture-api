package com.updown.api.account.infrastructure.repository;

import com.updown.api.account.domain.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountEntityRepository extends JpaRepository<AccountEntity, Long> {

    AccountEntity findAccountById(Long id);

}
