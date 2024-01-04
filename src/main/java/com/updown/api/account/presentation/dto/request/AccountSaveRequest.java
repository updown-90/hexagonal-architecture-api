package com.updown.api.account.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;


@Getter
public class AccountSaveRequest {

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;

    @NotEmpty
    private String accountName;

    @NotNull
    private Long departmentId;

}
