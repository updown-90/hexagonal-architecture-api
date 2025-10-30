package com.updown.api.account.application.port.out;

import com.updown.api.account.domain.Account;

import java.util.Optional;

/**
 * 계정 조회 아웃바운드 포트
 */
public interface LoadAccountOutPort {
    
    Optional<Account> loadAccount(Long accountId);
    
    Optional<Account> loadAccountByLoginId(String loginId);
}