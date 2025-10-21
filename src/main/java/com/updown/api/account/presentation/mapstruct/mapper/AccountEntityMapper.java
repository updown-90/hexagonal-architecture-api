package com.updown.api.account.presentation.mapstruct.mapper;


import com.updown.api.account.domain.AccountEntity;
import com.updown.api.account.domain.value.AccountName;
import com.updown.api.account.domain.value.LoginId;
import com.updown.api.account.presentation.dto.response.AccountFindResponse;
import com.updown.api.account.presentation.dto.response.AccountSaveResponse;
import com.updown.api.account.presentation.dto.response.AccountUpdateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountEntityMapper {

    AccountEntityMapper INSTANCE = Mappers.getMapper(AccountEntityMapper.class);

    default String map(LoginId loginId) {
        return loginId != null ? loginId.getValue() : null;
    }

    default String map(AccountName accountName) {
        return accountName != null ? accountName.getValue() : null;
    }

    AccountSaveResponse accountEntityToAccountSaveResponseDTO(AccountEntity accountEntity);
    AccountFindResponse accountEntityToAccountFindResponseDTO(AccountEntity accountEntity);
    AccountUpdateResponse accountEntityToAccountUpdateResponseDTO(AccountEntity accountEntity);

}
