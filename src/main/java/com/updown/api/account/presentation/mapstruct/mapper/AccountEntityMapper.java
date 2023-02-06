package com.updown.api.account.presentation.mapstruct.mapper;


import com.updown.api.account.domain.AccountEntity;
import com.updown.api.account.presentation.dto.response.AccountFindResponseDTO;
import com.updown.api.account.presentation.dto.response.AccountSaveResponseDTO;
import com.updown.api.account.presentation.dto.response.AccountUpdateResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountEntityMapper {

    AccountEntityMapper INSTANCE = Mappers.getMapper(AccountEntityMapper.class);

    AccountSaveResponseDTO accountEntityToAccountSaveResponseDTO(AccountEntity accountEntity);
    AccountFindResponseDTO accountEntityToAccountFindResponseDTO(AccountEntity accountEntity);
    AccountUpdateResponseDTO accountEntityToAccountUpdateResponseDTO(AccountEntity accountEntity);

}
