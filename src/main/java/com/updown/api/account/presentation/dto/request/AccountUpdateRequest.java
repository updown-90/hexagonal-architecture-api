package com.updown.api.account.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;


@Getter
public class AccountUpdateRequest {

    @NotEmpty
    private Long id;

    @NotEmpty
    private String changeAccountName;

}
