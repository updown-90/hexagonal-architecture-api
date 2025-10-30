package com.updown.api.account.application.service;

import com.updown.api.account.application.port.in.CreateAccountInPort;
import com.updown.api.account.application.port.in.dto.CreateAccountCommand;
import com.updown.api.account.application.port.in.dto.AccountResponse;
import com.updown.api.account.application.port.out.LoadAccountOutPort;
import com.updown.api.account.application.port.out.LoadDepartmentOutPort;
import com.updown.api.account.application.port.out.SaveAccountOutPort;
import com.updown.api.account.domain.Account;
import com.updown.api.department.domain.Department;
import com.updown.api.common.exception.CustomRuntimeException;
import com.updown.api.common.exception.ExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 계정 생성 서비스
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CreateAccountService implements CreateAccountInPort {
    
    private final LoadAccountOutPort loadAccountOutPort;
    private final LoadDepartmentOutPort loadDepartmentOutPort;
    private final SaveAccountOutPort saveAccountOutPort;
    
    @Override
    public AccountResponse createAccount(CreateAccountCommand command) {
        // 부서 존재 여부 확인
        Department department = loadDepartmentOutPort.loadDepartment(command.getDepartmentId())
                .orElseThrow(() -> new CustomRuntimeException(ExceptionType.NOT_FOUND_DEPARTMENT));
        
        // 로그인 ID 중복 확인
        if (loadAccountOutPort.loadAccountByLoginId(command.getLoginId()).isPresent()) {
            throw new CustomRuntimeException(ExceptionType.DUPLICATE_USER);
        }
        
        // 계정 생성
        Account account = Account.create(command, department);
        Account savedAccount = saveAccountOutPort.saveAccount(account);
        
        return AccountResponse.builder()
                .id(savedAccount.getId().getValue())
                .loginId(savedAccount.getLoginId().getValue())
                .accountName(savedAccount.getAccountName().getValue())
                .accountStatus(savedAccount.getAccountStatus().name())
                .departmentId(savedAccount.getDepartment().getId().getValue())
                .departmentName(savedAccount.getDepartment().getName().getValue())
                .build();
    }
}
