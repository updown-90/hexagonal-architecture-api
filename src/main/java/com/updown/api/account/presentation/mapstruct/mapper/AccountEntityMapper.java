package com.updown.api.account.presentation.mapstruct.mapper;


import com.updown.api.account.domain.AccountEntity;
import com.updown.api.account.presentation.dto.response.AccountFindResponse;
import com.updown.api.account.presentation.dto.response.AccountSaveResponse;
import com.updown.api.account.presentation.dto.response.AccountUpdateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountEntityMapper {

    AccountEntityMapper INSTANCE = Mappers.getMapper(AccountEntityMapper.class);

    AccountSaveResponse accountEntityToAccountSaveResponseDTO(AccountEntity accountEntity);
    AccountFindResponse accountEntityToAccountFindResponseDTO(AccountEntity accountEntity);
    AccountUpdateResponse accountEntityToAccountUpdateResponseDTO(AccountEntity accountEntity);

}
