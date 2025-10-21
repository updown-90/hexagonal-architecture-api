package com.updown.api.account.infrastructure.repository;

import com.updown.api.account.domain.AccountEntity;
import com.updown.api.account.domain.value.LoginId;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountEntityRepository extends JpaRepository<AccountEntity, Long>, AccountEntityQueryDSLRepository {

    Optional<AccountEntity> findAccountById(Long id);

    //  비관적 락(Pessimistic Lock)
    //  동일한 데이터를 동시에 수정할 가능성이 높다는 비관적인 전제로 잠금을 거는 방식입니다.
    //  예를 들어 상품의 재고는 동시에 같은 상품을 여러명이 주문할 수 있으므로 데이터 수정에 의한 경합이 발생할 가능성이 높다고 비관적으로 보는 것입니다.
    //  이 경우 충돌감지를 통해서 잠금을 발생시키면 충돌발생에 의한 예외가 자주 발생하게 됩니다. 이럴경우 비관적 잠금을 통해서 예외를 발생시키지 않고 정합성을 보장하는 것이 가능합니다.
    //  다만 성능적인 측면은 손실을 감수해야 합니다. 주로 데이터베이스에서 제공하는 배타잠금(Exclusive Lock)을 사용합니다.
    //  비관적락은 쿼리할 때 우선적으로 락을 거는 기법이다.
    //  쿼리 로그를 확인해 보면 데이터베이스에서 제공하는 락 기능인 select for update 구문이 추가된 것을 확인할 수 있다.
    //  SELECT ~ FOR UPDATE 구문은 "데이터 수정하려고 SELECT 하는 중이야 다른 사람들은 데이터에 손 대지 마!" 라고 할 수 있습니다
    //  좀 더 딱딱한 표현으로는 동시성 제어를 위하여 특정 데이터(ROW)에 대해 베타적 LOCK을 거는 기능입니다.

    //  @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    Optional<AccountEntity> findAccountByLoginId(LoginId loginId);

    @Cacheable(value = "accountEntity")
    List<AccountEntity> findAll();
}
