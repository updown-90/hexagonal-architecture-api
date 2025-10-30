package com.updown.api.account.application.port.in;

import com.updown.api.account.application.port.in.dto.AccountResponse;

/**
 * 계정 조회 인바운드 포트
 */
public interface GetAccountInPort {
    
    AccountResponse getAccount(Long accountId);
}