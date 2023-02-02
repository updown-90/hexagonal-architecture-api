package com.updown.api.account.service;

import com.updown.api.account.domain.AccountEntity;
import com.updown.api.account.infrastructure.repository.AccountEntityRepository;
import com.updown.api.account.presentation.dto.AccountSaveRequestDTO;
import com.updown.api.account.presentation.dto.AccountUpdateRequestDTO;
import com.updown.api.common.exception.CustomRuntimeException;
import com.updown.api.common.exception.ExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountEntityRepository accountEntityRepository;

    public AccountEntity createAccount(AccountSaveRequestDTO accountSaveRequestDTO) {
        return isEmptyDBLoginId(accountSaveRequestDTO) ?
                accountEntityRepository.save(AccountEntity.create(accountSaveRequestDTO)) : null;
    }

    public AccountEntity findAccountById(Long id) {
        return accountEntityRepository.findAccountById(id)
                .orElseThrow(() -> new CustomRuntimeException(ExceptionType.NOT_FOUND_USER));
    }

    public List<AccountEntity> findAllAccount() {
        return accountEntityRepository.findAll();
    }

    public AccountEntity updateAccount(AccountUpdateRequestDTO accountUpdateRequestDTO) {
        AccountEntity accountEntity = accountEntityRepository.findById(accountUpdateRequestDTO.getId())
                .orElseThrow(() -> new CustomRuntimeException(ExceptionType.NOT_FOUND_USER));

        accountEntity.update(accountUpdateRequestDTO);
        return accountEntityRepository.save(accountEntity);
    }

    public void deleteAccount(Long id) {
        accountEntityRepository.deleteById(id);
    }

    private boolean isEmptyDBLoginId(AccountSaveRequestDTO accountSaveRequestDTO) {
        accountEntityRepository.findAccountByLoginId(accountSaveRequestDTO.getLoginId())
            .ifPresent(accountEntity -> {   // ifPresent()는 Optional 객체가 값을 가지고 있으면 실행 값이 없으면 넘어감
                throw new CustomRuntimeException(ExceptionType.NOT_FOUND_USER);
            });

        return true;
    }
}
