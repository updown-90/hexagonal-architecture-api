package com.updown.api.account.application.port.in.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 계정 응답 DTO
 */
@Data
@Builder
public class AccountResponse {
    
    private Long id;
    private String loginId;
    private String accountName;
    private String accountStatus;
    private Long departmentId;
    private String departmentName;
}
