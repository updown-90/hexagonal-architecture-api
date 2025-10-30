package com.updown.api.account.application.service;

import com.updown.api.account.application.port.in.dto.CreateAccountCommand;
import com.updown.api.account.application.port.in.dto.AccountResponse;
import com.updown.api.account.application.port.out.LoadAccountOutPort;
import com.updown.api.department.application.port.out.LoadDepartmentOutPort;
import com.updown.api.account.application.port.out.SaveAccountOutPort;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * CreateAccountService 테스트
 */
@ExtendWith(MockitoExtension.class)
class CreateAccountServiceTest {

    @Mock
    private LoadAccountOutPort loadAccountOutPort;

    @Mock
    private LoadDepartmentOutPort loadDepartmentOutPort;

    @Mock
    private SaveAccountOutPort saveAccountOutPort;

    @InjectMocks
    private CreateAccountService createAccountService;

    @Test
    @DisplayName("계정을 성공적으로 생성할 수 있다")
    void createAccountSuccess() {
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

        Account savedAccount = Account.builder()
                .id(com.updown.api.account.domain.vo.AccountId.of(1L))
                .loginId(com.updown.api.account.domain.vo.LoginId.of("testuser"))
                .password(com.updown.api.account.domain.vo.Password.of("password123"))
                .accountName(com.updown.api.account.domain.vo.AccountName.of("테스트사용자"))
                .accountStatus(AccountStatus.NORMAL)
                .department(department)
                .build();

        given(loadDepartmentOutPort.loadDepartment(1L)).willReturn(Optional.of(department));
        given(loadAccountOutPort.loadAccountByLoginId("testuser")).willReturn(Optional.empty());
        given(saveAccountOutPort.saveAccount(any(Account.class))).willReturn(savedAccount);

        // when
        AccountResponse response = createAccountService.createAccount(command);

        // then
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getLoginId()).isEqualTo("testuser");
        assertThat(response.getAccountName()).isEqualTo("테스트사용자");
        assertThat(response.getAccountStatus()).isEqualTo("NORMAL");
        assertThat(response.getDepartmentId()).isEqualTo(1L);
        assertThat(response.getDepartmentName()).isEqualTo("개발팀");

        then(loadDepartmentOutPort).should().loadDepartment(1L);
        then(loadAccountOutPort).should().loadAccountByLoginId("testuser");
        then(saveAccountOutPort).should().saveAccount(any(Account.class));
    }

    @Test
    @DisplayName("존재하지 않는 부서로 계정 생성 시 예외가 발생한다")
    void createAccountWithNonExistentDepartment() {
        // given
        CreateAccountCommand command = CreateAccountCommand.builder()
                .loginId("testuser")
                .password("password123")
                .accountName("테스트사용자")
                .departmentId(999L)
                .build();

        given(loadDepartmentOutPort.loadDepartment(999L)).willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> createAccountService.createAccount(command))
                .isInstanceOf(CustomRuntimeException.class)
                .hasFieldOrPropertyWithValue("exceptionType", ExceptionType.NOT_FOUND_DEPARTMENT);
    }

    @Test
    @DisplayName("중복된 로그인 ID로 계정 생성 시 예외가 발생한다")
    void createAccountWithDuplicateLoginId() {
        // given
        CreateAccountCommand command = CreateAccountCommand.builder()
                .loginId("duplicateuser")
                .password("password123")
                .accountName("중복사용자")
                .departmentId(1L)
                .build();

        Department department = Department.builder()
                .id(DepartmentId.of(1L))
                .name(DepartmentName.of("개발팀"))
                .build();

        Account existingAccount = Account.builder()
                .id(com.updown.api.account.domain.vo.AccountId.of(1L))
                .loginId(com.updown.api.account.domain.vo.LoginId.of("duplicateuser"))
                .password(com.updown.api.account.domain.vo.Password.of("password123"))
                .accountName(com.updown.api.account.domain.vo.AccountName.of("기존사용자"))
                .accountStatus(AccountStatus.NORMAL)
                .department(department)
                .build();

        given(loadDepartmentOutPort.loadDepartment(1L)).willReturn(Optional.of(department));
        given(loadAccountOutPort.loadAccountByLoginId("duplicateuser")).willReturn(Optional.of(existingAccount));

        // when & then
        assertThatThrownBy(() -> createAccountService.createAccount(command))
                .isInstanceOf(CustomRuntimeException.class)
                .hasFieldOrPropertyWithValue("exceptionType", ExceptionType.DUPLICATE_USER);
    }
}
