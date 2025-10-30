package com.updown.api.account.application.service;

import com.updown.api.account.application.port.in.DeleteAccountInPort;
import com.updown.api.account.application.port.out.LoadAccountOutPort;
import com.updown.api.account.application.port.out.SaveAccountOutPort;
import com.updown.api.common.exception.CustomRuntimeException;
import com.updown.api.common.exception.ExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 계정 삭제 서비스
 */
@Service
@RequiredArgsConstructor
@Transactional
public class DeleteAccountService implements DeleteAccountInPort {
    
    private final LoadAccountOutPort loadAccountOutPort;
    private final SaveAccountOutPort saveAccountOutPort;
    
    @Override
    public void deleteAccount(Long accountId) {
        // 계정 존재 여부 확인
        loadAccountOutPort.loadAccount(accountId)
                .orElseThrow(() -> new CustomRuntimeException(ExceptionType.NOT_FOUND_USER));
        
        saveAccountOutPort.deleteAccount(accountId);
    }
}
