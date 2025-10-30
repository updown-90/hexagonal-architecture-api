package com.updown.api.account.application.service;

import com.updown.api.account.application.port.in.dto.AccountResponse;
import com.updown.api.account.application.port.out.LoadAccountOutPort;
import com.updown.api.account.domain.Account;
import com.updown.api.account.domain.AccountStatus;
import com.updown.api.common.exception.CustomRuntimeException;
import com.updown.api.common.exception.ExceptionType;
import com.updown.api.department.domain.Department;
import com.updown.api.department.domain.vo.DepartmentId;
import com.updown.api.department.domain.vo.DepartmentName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

/**
 * GetAccountService 테스트
 */
@ExtendWith(MockitoExtension.class)
class GetAccountServiceTest {

    @Mock
    private LoadAccountOutPort loadAccountOutPort;

    @InjectMocks
    private GetAccountService getAccountService;

    @Test
    @DisplayName("정상 계정을 조회할 수 있다")
    void getNormalAccount() {
        // given
        Long accountId = 1L;
        Department department = Department.builder()
                .id(DepartmentId.of(1L))
                .name(DepartmentName.of("개발팀"))
                .build();

        Account account = Account.builder()
                .id(com.updown.api.account.domain.vo.AccountId.of(accountId))
                .loginId(com.updown.api.account.domain.vo.LoginId.of("testuser"))
                .password(com.updown.api.account.domain.vo.Password.of("password123"))
                .accountName(com.updown.api.account.domain.vo.AccountName.of("테스트사용자"))
                .accountStatus(AccountStatus.NORMAL)
                .department(department)
                .build();

        given(loadAccountOutPort.loadAccount(accountId)).willReturn(Optional.of(account));

        // when
        AccountResponse response = getAccountService.getAccount(accountId);

        // then
        assertThat(response.getId()).isEqualTo(accountId);
        assertThat(response.getLoginId()).isEqualTo("testuser");
        assertThat(response.getAccountName()).isEqualTo("테스트사용자");
        assertThat(response.getAccountStatus()).isEqualTo("NORMAL");
        assertThat(response.getDepartmentId()).isEqualTo(1L);
        assertThat(response.getDepartmentName()).isEqualTo("개발팀");
    }

    @Test
    @DisplayName("존재하지 않는 계정 조회 시 예외가 발생한다")
    void getNonExistentAccount() {
        // given
        Long accountId = 999L;
        given(loadAccountOutPort.loadAccount(accountId)).willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> getAccountService.getAccount(accountId))
                .isInstanceOf(CustomRuntimeException.class)
                .hasFieldOrPropertyWithValue("exceptionType", ExceptionType.NOT_FOUND_USER);
    }

    @Test
    @DisplayName("정지된 계정 조회 시 예외가 발생한다")
    void getSuspendedAccount() {
        // given
        Long accountId = 1L;
        Department department = Department.builder()
                .id(DepartmentId.of(1L))
                .name(DepartmentName.of("개발팀"))
                .build();

        Account suspendedAccount = Account.builder()
                .id(com.updown.api.account.domain.vo.AccountId.of(accountId))
                .loginId(com.updown.api.account.domain.vo.LoginId.of("suspendeduser"))
                .password(com.updown.api.account.domain.vo.Password.of("password123"))
                .accountName(com.updown.api.account.domain.vo.AccountName.of("정지사용자"))
                .accountStatus(AccountStatus.SUSPENDED)
                .department(department)
                .build();

        given(loadAccountOutPort.loadAccount(accountId)).willReturn(Optional.of(suspendedAccount));

        // when & then
        assertThatThrownBy(() -> getAccountService.getAccount(accountId))
                .isInstanceOf(CustomRuntimeException.class)
                .hasFieldOrPropertyWithValue("exceptionType", ExceptionType.NOT_NORMAL_USER);
    }
}
