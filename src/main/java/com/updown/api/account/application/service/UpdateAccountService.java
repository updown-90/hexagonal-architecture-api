package com.updown.api.account.application.service;

import com.updown.api.account.application.port.in.UpdateAccountInPort;
import com.updown.api.account.application.port.in.dto.UpdateAccountCommand;
import com.updown.api.account.application.port.in.dto.AccountResponse;
import com.updown.api.account.application.port.out.LoadAccountOutPort;
import com.updown.api.account.application.port.out.SaveAccountOutPort;
import com.updown.api.account.domain.Account;
import com.updown.api.common.exception.CustomRuntimeException;
import com.updown.api.common.exception.ExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 계정 수정 서비스
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UpdateAccountService implements UpdateAccountInPort {
    
    private final LoadAccountOutPort loadAccountOutPort;
    private final SaveAccountOutPort saveAccountOutPort;
    
    @Override
    public AccountResponse updateAccount(UpdateAccountCommand command) {
        Account account = loadAccountOutPort.loadAccount(command.getAccountId())
                .orElseThrow(() -> new CustomRuntimeException(ExceptionType.NOT_FOUND_USER));
        
        account.update(command);
        Account savedAccount = saveAccountOutPort.saveAccount(account);
        
        return AccountResponse.builder()
                .id(savedAccount.getId().getValue())
                .loginId(savedAccount.getLoginId().getValue())
                .accountName(savedAccount.getAccountName().getValue())
                .accountStatus(savedAccount.getAccountStatus().name())
                .departmentId(savedAccount.getDepartment().getId().getValue())
                .departmentName(savedAccount.getDepartment().getName().getValue())
                .build();
    }
}
