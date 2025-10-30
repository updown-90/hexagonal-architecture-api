package com.updown.api.account.application.port.in.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

/**
 * 계정 생성 커맨드
 */
@Data
@Builder
public class CreateAccountCommand {
    
    @NotEmpty
    private String loginId;
    
    @NotEmpty
    private String password;
    
    @NotEmpty
    private String accountName;
    
    @NotNull
    private Long departmentId;
}
