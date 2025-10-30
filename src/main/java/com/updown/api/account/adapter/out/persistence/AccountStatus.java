package com.updown.api.account.adapter.out.persistence;

/**
 * 계정 상태 JPA 열거형
 */
public enum AccountStatus {
    NORMAL,     // 정상
    SUSPENDED,  // 정지
    DELETED     // 삭제
}
