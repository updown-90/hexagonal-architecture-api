package com.updown.api.account.service;

import com.updown.api.account.domain.AccountEntity;
import com.updown.api.account.infrastructure.repository.AccountEntityRepository;
import com.updown.api.account.presentation.dto.AccountSaveRequestDTO;
import com.updown.api.account.presentation.dto.AccountUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountEntityRepository accountEntityRepository;

    public AccountEntity createAccount(AccountSaveRequestDTO accountSaveRequestDTO) {
        return accountEntityRepository.save(AccountEntity.create(accountSaveRequestDTO));
    }

    public AccountEntity findAccountById(Long id) {
        return accountEntityRepository.findAccountById(id);
    }

    public List<AccountEntity> findAllAccount() {
        return accountEntityRepository.findAll();
    }

    public AccountEntity updateAccount(AccountUpdateRequestDTO accountUpdateRequestDTO) throws Exception {
        return accountEntityRepository.save(accountEntityRepository.findById(accountUpdateRequestDTO.getId())
                .orElseThrow(Exception::new));
    }

    public void deleteAccount(Long id) {
        accountEntityRepository.deleteById(id);
    }
}
