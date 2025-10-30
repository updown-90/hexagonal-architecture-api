package com.updown.api.account.adapter.out.persistence;

import com.updown.api.account.application.port.out.LoadAccountOutPort;
import com.updown.api.account.application.port.out.SaveAccountOutPort;
import com.updown.api.account.domain.Account;
import com.updown.api.account.domain.vo.AccountId;
import com.updown.api.account.domain.vo.AccountName;
import com.updown.api.account.domain.vo.LoginId;
import com.updown.api.account.domain.vo.Password;
import com.updown.api.department.domain.Department;
import com.updown.api.department.domain.vo.DepartmentId;
import com.updown.api.department.domain.vo.DepartmentName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 계정 영속성 어댑터
 */
@Component
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements LoadAccountOutPort, SaveAccountOutPort {
    
    private final AccountRepository accountRepository;
    
    @Override
    public Optional<Account> loadAccount(Long accountId) {
        return accountRepository.findById(accountId)
                .map(this::mapToDomain);
    }
    
    @Override
    public Optional<Account> loadAccountByLoginId(String loginId) {
        return accountRepository.findByLoginId(loginId)
                .map(this::mapToDomain);
    }
    
    @Override
    public Account saveAccount(Account account) {
        AccountJpaEntity jpaEntity = mapToJpaEntity(account);
        AccountJpaEntity savedEntity = accountRepository.save(jpaEntity);
        return mapToDomain(savedEntity);
    }
    
    @Override
    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }
    
    private Account mapToDomain(AccountJpaEntity jpaEntity) {
        return Account.builder()
                .id(AccountId.of(jpaEntity.getId()))
                .loginId(LoginId.of(jpaEntity.getLoginId()))
                .password(Password.of(jpaEntity.getPassword()))
                .accountName(AccountName.of(jpaEntity.getAccountName()))
                .accountStatus(com.updown.api.account.domain.AccountStatus.valueOf(jpaEntity.getAccountStatus().name()))
                .department(Department.builder()
                        .id(DepartmentId.of(jpaEntity.getDepartment().getId()))
                        .name(DepartmentName.of(jpaEntity.getDepartment().getName()))
                        .build())
                .version(jpaEntity.getVersion())
                .build();
    }
    
    private AccountJpaEntity mapToJpaEntity(Account account) {
        return AccountJpaEntity.builder()
                .id(account.getId() != null ? account.getId().getValue() : null)
                .loginId(account.getLoginId().getValue())
                .password(account.getPassword().getValue())
                .accountName(account.getAccountName().getValue())
                .accountStatus(AccountStatus.valueOf(account.getAccountStatus().name()))
                .version(account.getVersion())
                .build();
    }
}
