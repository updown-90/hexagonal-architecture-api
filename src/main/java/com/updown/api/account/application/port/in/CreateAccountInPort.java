package com.updown.api.account.application.port.in;

import com.updown.api.account.application.port.in.dto.CreateAccountCommand;
import com.updown.api.account.application.port.in.dto.AccountResponse;

/**
 * 계정 생성 인바운드 포트
 */
public interface CreateAccountInPort {
    
    AccountResponse createAccount(CreateAccountCommand command);
}