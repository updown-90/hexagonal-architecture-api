package com.updown.api.account.service;

import com.updown.api.account.domain.AccountEntity;
import com.updown.api.account.infrastructure.dto.AccountEntityQueryDSLDTO;
import com.updown.api.account.infrastructure.repository.AccountEntityRepository;
import com.updown.api.account.presentation.dto.request.AccountSaveRequestDTO;
import com.updown.api.account.presentation.dto.request.AccountUpdateRequestDTO;
import com.updown.api.account.presentation.dto.request.AccountsFindRequestDTO;
import com.updown.api.common.exception.CustomRuntimeException;
import com.updown.api.common.exception.ExceptionType;
import com.updown.api.department.domain.DepartmentEntity;
import com.updown.api.department.service.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountEntityRepository accountEntityRepository;
    private final DepartmentRepository departmentRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountEntity createAccount(AccountSaveRequestDTO accountSaveRequestDTO) {
        DepartmentEntity departmentEntity = departmentRepository.findById(accountSaveRequestDTO.getDepartmentId()).get();


        AccountEntity entity = AccountEntity.create(accountSaveRequestDTO, departmentEntity);
        entity.encodePassWord(passwordEncoder);

        return isEmptyDBLoginId(accountSaveRequestDTO) ?
                accountEntityRepository.save(entity) : null;
    }

    public AccountEntity findAccountById(Long id) {
        return accountEntityRepository.findAccountById(id)
                .orElseThrow(() -> new CustomRuntimeException(ExceptionType.NOT_FOUND_USER));
    }

    public List<AccountEntityQueryDSLDTO> findAccounts(AccountsFindRequestDTO accountsFindRequestDTO) {
        return accountEntityRepository.findAccounts(accountsFindRequestDTO);
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
