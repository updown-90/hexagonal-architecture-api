package com.updown.api.account.application.port.in.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

/**
 * 계정 수정 커맨드
 */
@Data
@Builder
public class UpdateAccountCommand {
    
    @NotNull
    private Long accountId;
    
    @NotEmpty
    private String accountName;
}
