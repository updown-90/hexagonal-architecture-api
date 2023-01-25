package com.updown.api.account.presentation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AccountSaveRequestDTO {

    private String loginId;

    private String accountName;

}
