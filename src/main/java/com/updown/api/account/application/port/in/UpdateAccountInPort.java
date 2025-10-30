package com.updown.api.account.application.port.in;

import com.updown.api.account.application.port.in.dto.UpdateAccountCommand;
import com.updown.api.account.application.port.in.dto.AccountResponse;

/**
 * 계정 수정 인바운드 포트
 */
public interface UpdateAccountInPort {
    
    AccountResponse updateAccount(UpdateAccountCommand command);
}