package com.updown.api.account.domain;

import com.updown.api.account.domain.vo.AccountId;
import com.updown.api.account.domain.vo.AccountName;
import com.updown.api.account.domain.vo.LoginId;
import com.updown.api.account.domain.vo.Password;
import com.updown.api.account.application.port.in.dto.CreateAccountCommand;
import com.updown.api.account.application.port.in.dto.UpdateAccountCommand;
import com.updown.api.department.domain.Department;
import lombok.Builder;
import lombok.Getter;

/**
 * 계정 도메인 엔티티
 */
@Getter
public class Account {
    
    private final AccountId id;
    private final LoginId loginId;
    private Password password;
    private AccountName accountName;
    private AccountStatus accountStatus;
    private final Department department;
    private Long version;
    
    @Builder
    public Account(AccountId id, LoginId loginId, Password password, AccountName accountName, 
                  AccountStatus accountStatus, Department department, Long version) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.accountName = accountName;
        this.accountStatus = accountStatus;
        this.department = department;
        this.version = version;
    }
    
    /**
     * 계정 생성 팩토리 메서드
     */
    public static Account create(CreateAccountCommand command, Department department) {
        return Account.builder()
                .loginId(LoginId.of(command.getLoginId()))
                .password(Password.of(command.getPassword()))
                .accountName(AccountName.of(command.getAccountName()))
                .accountStatus(AccountStatus.NORMAL)
                .department(department)
                .build();
    }
    
    /**
     * 계정 정보 수정
     */
    public void update(UpdateAccountCommand command) {
        this.accountName = AccountName.of(command.getAccountName());
    }
    
    /**
     * 계정이 정상 상태인지 확인
     */
    public boolean isNormal() {
        return this.accountStatus == AccountStatus.NORMAL;
    }
}
