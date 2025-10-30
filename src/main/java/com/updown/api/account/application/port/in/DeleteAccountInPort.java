package com.updown.api.account.application.port.in;

/**
 * 계정 삭제 인바운드 포트
 */
public interface DeleteAccountInPort {
    
    void deleteAccount(Long accountId);
}