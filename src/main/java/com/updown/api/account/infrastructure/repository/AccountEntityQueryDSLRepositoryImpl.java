package com.updown.api.account.infrastructure.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.updown.api.account.infrastructure.dto.AccountEntityQueryDSLResponse;
import com.updown.api.account.infrastructure.dto.QAccountEntityQueryDSLDTO;
import com.updown.api.account.presentation.dto.request.AccountsFindRequest;

import javax.persistence.EntityManager;
import java.util.List;

import static com.updown.api.account.domain.QAccountEntity.accountEntity;


public class AccountEntityQueryDSLRepositoryImpl implements AccountEntityQueryDSLRepository{

    private final JPAQueryFactory queryFactory;

    public AccountEntityQueryDSLRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<AccountEntityQueryDSLResponse> findAccounts(AccountsFindRequest accountsFindRequest) {
        return queryFactory.select(new QAccountEntityQueryDSLDTO(
                accountEntity.loginId,
                accountEntity.accountName)
        ).from(accountEntity)
        .fetch();
    }
}
