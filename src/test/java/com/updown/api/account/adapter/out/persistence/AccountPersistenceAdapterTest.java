package com.updown.api.account.adapter.out.persistence;

import com.updown.api.account.domain.Account;
import com.updown.api.account.domain.AccountStatus;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * AccountPersistenceAdapter 테스트
 */
@ExtendWith(MockitoExtension.class)
class AccountPersistenceAdapterTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountPersistenceAdapter accountPersistenceAdapter;

    @Test
    @DisplayName("ID로 계정을 조회할 수 있다")
    void loadAccountById() {
        // given
        Long accountId = 1L;
        com.updown.api.department.adapter.out.persistence.DepartmentJpaEntity departmentJpaEntity = 
                com.updown.api.department.adapter.out.persistence.DepartmentJpaEntity.builder()
                        .id(1L)
                        .name("개발팀")
                        .build();

        AccountJpaEntity jpaEntity = AccountJpaEntity.builder()
                .id(accountId)
                .loginId("testuser")
                .password("password123")
                .accountName("테스트사용자")
                .accountStatus(com.updown.api.account.adapter.out.persistence.AccountStatus.NORMAL)
                .version(1L)
                .department(departmentJpaEntity)
                .build();

        given(accountRepository.findById(accountId)).willReturn(Optional.of(jpaEntity));

        // when
        Optional<Account> result = accountPersistenceAdapter.loadAccount(accountId);

        // then
        assertThat(result).isPresent();
        Account account = result.get();
        assertThat(account.getId().getValue()).isEqualTo(accountId);
        assertThat(account.getLoginId().getValue()).isEqualTo("testuser");
        assertThat(account.getAccountName().getValue()).isEqualTo("테스트사용자");
        assertThat(account.getAccountStatus()).isEqualTo(com.updown.api.account.domain.AccountStatus.NORMAL);
        assertThat(account.getDepartment().getId().getValue()).isEqualTo(1L);
        assertThat(account.getDepartment().getName().getValue()).isEqualTo("개발팀");

        then(accountRepository).should().findById(accountId);
    }

    @Test
    @DisplayName("로그인 ID로 계정을 조회할 수 있다")
    void loadAccountByLoginId() {
        // given
        String loginId = "testuser";
        com.updown.api.department.adapter.out.persistence.DepartmentJpaEntity departmentJpaEntity = 
                com.updown.api.department.adapter.out.persistence.DepartmentJpaEntity.builder()
                        .id(1L)
                        .name("개발팀")
                        .build();

        AccountJpaEntity jpaEntity = AccountJpaEntity.builder()
                .id(1L)
                .loginId(loginId)
                .password("password123")
                .accountName("테스트사용자")
                .accountStatus(com.updown.api.account.adapter.out.persistence.AccountStatus.NORMAL)
                .version(1L)
                .department(departmentJpaEntity)
                .build();

        given(accountRepository.findByLoginId(loginId)).willReturn(Optional.of(jpaEntity));

        // when
        Optional<Account> result = accountPersistenceAdapter.loadAccountByLoginId(loginId);

        // then
        assertThat(result).isPresent();
        Account account = result.get();
        assertThat(account.getLoginId().getValue()).isEqualTo(loginId);
        assertThat(account.getAccountName().getValue()).isEqualTo("테스트사용자");

        then(accountRepository).should().findByLoginId(loginId);
    }

    @Test
    @DisplayName("계정을 저장할 수 있다")
    void saveAccount() {
        // given
        Department department = Department.builder()
                .id(DepartmentId.of(1L))
                .name(DepartmentName.of("개발팀"))
                .build();

        Account account = Account.builder()
                .loginId(com.updown.api.account.domain.vo.LoginId.of("testuser"))
                .password(com.updown.api.account.domain.vo.Password.of("password123"))
                .accountName(com.updown.api.account.domain.vo.AccountName.of("테스트사용자"))
                .accountStatus(com.updown.api.account.domain.AccountStatus.NORMAL)
                .department(department)
                .build();

        com.updown.api.department.adapter.out.persistence.DepartmentJpaEntity departmentJpaEntity = 
                com.updown.api.department.adapter.out.persistence.DepartmentJpaEntity.builder()
                        .id(1L)
                        .name("개발팀")
                        .build();

        AccountJpaEntity savedJpaEntity = AccountJpaEntity.builder()
                .id(1L)
                .loginId("testuser")
                .password("password123")
                .accountName("테스트사용자")
                .accountStatus(com.updown.api.account.adapter.out.persistence.AccountStatus.NORMAL)
                .version(1L)
                .department(departmentJpaEntity)
                .build();

        given(accountRepository.save(any(AccountJpaEntity.class))).willReturn(savedJpaEntity);

        // when
        Account result = accountPersistenceAdapter.saveAccount(account);

        // then
        assertThat(result.getId().getValue()).isEqualTo(1L);
        assertThat(result.getLoginId().getValue()).isEqualTo("testuser");
        assertThat(result.getAccountName().getValue()).isEqualTo("테스트사용자");
        assertThat(result.getAccountStatus()).isEqualTo(com.updown.api.account.domain.AccountStatus.NORMAL);

        then(accountRepository).should().save(any(AccountJpaEntity.class));
    }

    @Test
    @DisplayName("계정을 삭제할 수 있다")
    void deleteAccount() {
        // given
        Long accountId = 1L;

        // when
        accountPersistenceAdapter.deleteAccount(accountId);

        // then
        then(accountRepository).should().deleteById(accountId);
    }
}
