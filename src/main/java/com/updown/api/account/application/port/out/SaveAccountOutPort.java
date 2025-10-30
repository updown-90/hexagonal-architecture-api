package com.updown.api.account.application.port.out;

import com.updown.api.account.domain.Account;

/**
 * 계정 저장 아웃바운드 포트
 */
public interface SaveAccountOutPort {
    
    Account saveAccount(Account account);
    
    void deleteAccount(Long accountId);
}