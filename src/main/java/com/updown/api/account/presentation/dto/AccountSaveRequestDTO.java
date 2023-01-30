package com.updown.api.account.presentation.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class AccountSaveRequestDTO {

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;

    @NotEmpty
    private String accountName;

}
