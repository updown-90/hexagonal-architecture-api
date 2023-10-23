package com.updown.api.account.infrastructure.repository;

import com.updown.api.account.infrastructure.dto.AccountEntityQueryDSLResponse;
import com.updown.api.account.presentation.dto.request.AccountsFindRequest;

import java.util.List;

public interface AccountEntityQueryDSLRepository {
    List<AccountEntityQueryDSLResponse> findAccounts(AccountsFindRequest accountsFindRequest);

}
