package com.updown.api.account.infrastructure.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class AccountEntityQueryDSLResponse {
    private String loginId;

    private String accountName;

    @QueryProjection
    public AccountEntityQueryDSLResponse(String loginId, String accountName) {
        this.loginId = loginId;
        this.accountName = accountName;
    }
}
