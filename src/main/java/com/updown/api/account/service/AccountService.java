package com.updown.api.account.service;

import com.updown.api.account.domain.AccountEntity;
import com.updown.api.account.domain.AccountStatus;
import com.updown.api.account.domain.value.LoginId;
import com.updown.api.account.infrastructure.dto.AccountEntityQueryDSLResponse;
import com.updown.api.account.infrastructure.repository.AccountEntityRepository;
import com.updown.api.account.presentation.dto.request.AccountSaveRequest;
import com.updown.api.account.presentation.dto.request.AccountUpdateRequest;
import com.updown.api.account.presentation.dto.request.AccountsFindRequest;
import com.updown.api.common.exception.CustomRuntimeException;
import com.updown.api.common.exception.ExceptionType;
import com.updown.api.department.service.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountEntityRepository accountEntityRepository;
    private final DepartmentRepository departmentRepository;

    public AccountEntity createAccount(AccountSaveRequest accountSaveRequest) {
        var departmentEntity = departmentRepository.findById(accountSaveRequest.getDepartmentId()).get();

        var entity = AccountEntity.create(accountSaveRequest, departmentEntity);

        return isEmptyDBLoginId(accountSaveRequest) ?
                accountEntityRepository.save(entity) : null;
    }

    public AccountEntity findAccountById(Long id) {
        var accountEntity = accountEntityRepository.findAccountById(id)
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

    public List<AccountEntityQueryDSLResponse> findAccounts(AccountsFindRequest accountsFindRequest) {
        return accountEntityRepository.findAccounts(accountsFindRequest);
    }

    public AccountEntity updateAccount(AccountUpdateRequest accountUpdateRequest) {
        var accountEntity = accountEntityRepository.findById(accountUpdateRequest.getId())
                .orElseThrow(() -> new CustomRuntimeException(ExceptionType.NOT_FOUND_USER));

        accountEntity.update(accountUpdateRequest);
        return accountEntityRepository.save(accountEntity);
    }

    public void deleteAccount(Long id) {
        accountEntityRepository.deleteById(id);
    }

    private boolean isEmptyDBLoginId(AccountSaveRequest accountSaveRequest) {
        // 로그인 ID가 이미 존재하는지 확인
        if (accountEntityRepository.findAccountByLoginId(LoginId.of(accountSaveRequest.getLoginId())).isPresent()) {
            throw new CustomRuntimeException(ExceptionType.DUPLICATE_USER);
        }
        return true;
    }
}
