package com.updown.api.account.service;

import com.updown.api.account.domain.AccountEntity;
import com.updown.api.account.domain.AccountStatus;
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
        AccountEntity accountEntity = accountEntityRepository.findAccountById(id)
                .orElseThrow(() -> new CustomRuntimeException(ExceptionType.NOT_FOUND_USER));

//      enum 사용시 비교는 등호연산자( == ) 를 활용하시는 것을 추천합니다.
//      equals 의 경우 NullPointException이 발생할 수 있습니다.
//      이를 피하기 위해 CODE.equals(code) 처럼 상수나 enum 을 앞에 두는 방식으로 코딩하기도 합니다.
//      == 의 경우 NullSafe 합니다. 컴파일 단계에서 Error 가 발생할 수 있어 IDE에서 발견하기 용이합니다.
        if (accountEntity.getAccountStatus() == AccountStatus.NORMAL) {
            throw new CustomRuntimeException(ExceptionType.NOT_NORMAL_USER);
        }

        return accountEntity;
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
