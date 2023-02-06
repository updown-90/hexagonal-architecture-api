package com.updown.api.account.infrastructure.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class AccountEntityQueryDSLDTO {
    private String loginId;

    private String accountName;

    @QueryProjection
    public AccountEntityQueryDSLDTO(String loginId, String accountName) {
        this.loginId = loginId;
        this.accountName = accountName;
    }
}
