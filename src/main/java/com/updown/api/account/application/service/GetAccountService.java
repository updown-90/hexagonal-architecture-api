package com.updown.api.account.application.service;

import com.updown.api.account.application.port.in.GetAccountInPort;
import com.updown.api.account.application.port.in.dto.AccountResponse;
import com.updown.api.account.application.port.out.LoadAccountOutPort;
import com.updown.api.account.domain.Account;
import com.updown.api.common.exception.CustomRuntimeException;
import com.updown.api.common.exception.ExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 계정 조회 서비스
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetAccountService implements GetAccountInPort {
    
    private final LoadAccountOutPort loadAccountOutPort;
    
    @Override
    public AccountResponse getAccount(Long accountId) {
        Account account = loadAccountOutPort.loadAccount(accountId)
                .orElseThrow(() -> new CustomRuntimeException(ExceptionType.NOT_FOUND_USER));
        
        // 정상 계정인지 확인
        if (!account.isNormal()) {
            throw new CustomRuntimeException(ExceptionType.NOT_NORMAL_USER);
        }
        
        return AccountResponse.builder()
                .id(account.getId().getValue())
                .loginId(account.getLoginId().getValue())
                .accountName(account.getAccountName().getValue())
                .accountStatus(account.getAccountStatus().name())
                .departmentId(account.getDepartment().getId().getValue())
                .departmentName(account.getDepartment().getName().getValue())
                .build();
    }
}
