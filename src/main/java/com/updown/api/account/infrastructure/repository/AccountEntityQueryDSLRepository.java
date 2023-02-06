package com.updown.api.account.infrastructure.repository;

import com.updown.api.account.infrastructure.dto.AccountEntityQueryDSLDTO;
import com.updown.api.account.presentation.dto.request.AccountsFindRequestDTO;

import java.util.List;

public interface AccountEntityQueryDSLRepository {
    List<AccountEntityQueryDSLDTO> findAccounts(AccountsFindRequestDTO accountsFindRequestDTO);

}
