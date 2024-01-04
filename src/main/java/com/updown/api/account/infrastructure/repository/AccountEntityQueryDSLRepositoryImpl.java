package com.updown.api.account.infrastructure.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.updown.api.account.domain.AccountEntity;
import com.updown.api.account.infrastructure.dto.AccountEntityQueryDSLResponse;
import com.updown.api.account.infrastructure.dto.QAccountEntityQueryDSLResponse;
import com.updown.api.account.presentation.dto.request.AccountsFindRequest;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.updown.api.account.domain.QAccountEntity.accountEntity;

public class AccountEntityQueryDSLRepositoryImpl extends QuerydslRepositorySupport implements AccountEntityQueryDSLRepository {

  public AccountEntityQueryDSLRepositoryImpl() {
    super(AccountEntity.class);
  }

  @Override
  public List<AccountEntityQueryDSLResponse> findAccounts(AccountsFindRequest accountsFindRequest) {
    return getQuerydsl().createQuery()
        .select(
            new QAccountEntityQueryDSLResponse(accountEntity.loginId, accountEntity.accountName))
        .from(accountEntity)
        .fetch();
  }
}
