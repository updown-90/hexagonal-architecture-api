package com.updown.api.account.service;

import com.updown.api.account.domain.AccountEntity;
import com.updown.api.account.domain.AccountStatus;
import com.updown.api.account.infrastructure.repository.AccountEntityRepository;
import jakarta.persistence.Cacheable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountEntityRepository accountEntityRepository;

// 데이터 1000건으로 조회 테스트

// 캐싱 처리 전
//    시작시간: 2024-02-16T12:47:03.248153
//    종료시간: 2024-02-16T12:47:04.330604

// 캐싱 처리 후
//    시작시간: 2024-02-16T12:49:16.757843
//    종료시간: 2024-02-16T12:49:16.832958
    @Test
    void 계정_조회_테스트_DB() {

        for (int i = 0; i < 1000; i++) {
            AccountEntity accountEntity = AccountEntity.builder().
                    loginId("loginId" + i).
                    accountName("accountName" + i).
                    password("password").
                    accountStatus(AccountStatus.NORMAL).
                    departmentEntity(null).
                    build();

            accountEntityRepository.save(accountEntity);
        }

        System.out.println("시작시간: " + LocalDateTime.now());
        for (int i = 0; i < 1000; i++) {
            accountEntityRepository.findAll();
        }
        System.out.println("종료시간: " + LocalDateTime.now());
    }

}