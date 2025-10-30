package com.updown.api.account.domain;

import com.updown.api.account.application.port.in.dto.CreateAccountCommand;
import com.updown.api.account.application.port.in.dto.UpdateAccountCommand;
import com.updown.api.department.domain.Department;
import com.updown.api.department.domain.vo.DepartmentId;
import com.updown.api.department.domain.vo.DepartmentName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Account 도메인 테스트
 */
class AccountTest {

    @Test
    @DisplayName("계정을 생성할 수 있다")
    void createAccount() {
        // given
        CreateAccountCommand command = CreateAccountCommand.builder()
                .loginId("testuser")
                .password("password123")
                .accountName("테스트사용자")
                .departmentId(1L)
                .build();

        Department department = Department.builder()
                .id(DepartmentId.of(1L))
                .name(DepartmentName.of("개발팀"))
                .build();

        // when
        Account account = Account.create(command, department);

        // then
        assertThat(account.getLoginId().getValue()).isEqualTo("testuser");
        assertThat(account.getPassword().getValue()).isEqualTo("password123");
        assertThat(account.getAccountName().getValue()).isEqualTo("테스트사용자");
        assertThat(account.getAccountStatus()).isEqualTo(AccountStatus.NORMAL);
        assertThat(account.getDepartment()).isEqualTo(department);
    }

    @Test
    @DisplayName("계정 정보를 수정할 수 있다")
    void updateAccount() {
        // given
        Account account = Account.builder()
                .id(com.updown.api.account.domain.vo.AccountId.of(1L))
                .loginId(com.updown.api.account.domain.vo.LoginId.of("testuser"))
                .password(com.updown.api.account.domain.vo.Password.of("password123"))
                .accountName(com.updown.api.account.domain.vo.AccountName.of("기존이름"))
                .accountStatus(AccountStatus.NORMAL)
                .department(Department.builder()
                        .id(DepartmentId.of(1L))
                        .name(DepartmentName.of("개발팀"))
                        .build())
                .build();

        UpdateAccountCommand command = UpdateAccountCommand.builder()
                .accountId(1L)
                .accountName("새로운이름")
                .build();

        // when
        account.update(command);

        // then
        assertThat(account.getAccountName().getValue()).isEqualTo("새로운이름");
    }

    @Test
    @DisplayName("정상 계정인지 확인할 수 있다")
    void isNormal() {
        // given
        Account normalAccount = Account.builder()
                .id(com.updown.api.account.domain.vo.AccountId.of(1L))
                .loginId(com.updown.api.account.domain.vo.LoginId.of("testuser"))
                .password(com.updown.api.account.domain.vo.Password.of("password123"))
                .accountName(com.updown.api.account.domain.vo.AccountName.of("테스트사용자"))
                .accountStatus(AccountStatus.NORMAL)
                .department(Department.builder()
                        .id(DepartmentId.of(1L))
                        .name(DepartmentName.of("개발팀"))
                        .build())
                .build();

        Account suspendedAccount = Account.builder()
                .id(com.updown.api.account.domain.vo.AccountId.of(2L))
                .loginId(com.updown.api.account.domain.vo.LoginId.of("suspendeduser"))
                .password(com.updown.api.account.domain.vo.Password.of("password123"))
                .accountName(com.updown.api.account.domain.vo.AccountName.of("정지사용자"))
                .accountStatus(AccountStatus.SUSPENDED)
                .department(Department.builder()
                        .id(DepartmentId.of(1L))
                        .name(DepartmentName.of("개발팀"))
                        .build())
                .build();

        // when & then
        assertThat(normalAccount.isNormal()).isTrue();
        assertThat(suspendedAccount.isNormal()).isFalse();
    }
}
