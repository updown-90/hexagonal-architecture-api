package com.updown.api.common;

import com.updown.api.account.application.port.in.dto.CreateAccountCommand;
import com.updown.api.account.application.port.in.dto.UpdateAccountCommand;
import com.updown.api.account.domain.Account;
import com.updown.api.account.domain.AccountStatus;
import com.updown.api.department.application.port.in.dto.CreateDepartmentCommand;
import com.updown.api.department.application.port.in.dto.UpdateDepartmentCommand;
import com.updown.api.department.domain.Department;

/**
 * 테스트 데이터 빌더 유틸리티
 */
public class TestDataBuilder {

    public static class AccountBuilder {
        private Long id = 1L;
        private String loginId = "testuser";
        private String password = "password123";
        private String accountName = "테스트사용자";
        private AccountStatus accountStatus = AccountStatus.NORMAL;
        private Department department = Department.builder()
                .id(com.updown.api.department.domain.vo.DepartmentId.of(1L))
                .name(com.updown.api.department.domain.vo.DepartmentName.of("개발팀"))
                .build();
        private Long version = 1L;

        public AccountBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AccountBuilder loginId(String loginId) {
            this.loginId = loginId;
            return this;
        }

        public AccountBuilder password(String password) {
            this.password = password;
            return this;
        }

        public AccountBuilder accountName(String accountName) {
            this.accountName = accountName;
            return this;
        }

        public AccountBuilder accountStatus(AccountStatus accountStatus) {
            this.accountStatus = accountStatus;
            return this;
        }

        public AccountBuilder department(Department department) {
            this.department = department;
            return this;
        }

        public AccountBuilder version(Long version) {
            this.version = version;
            return this;
        }

        public Account build() {
            return Account.builder()
                    .id(com.updown.api.account.domain.vo.AccountId.of(id))
                    .loginId(com.updown.api.account.domain.vo.LoginId.of(loginId))
                    .password(com.updown.api.account.domain.vo.Password.of(password))
                    .accountName(com.updown.api.account.domain.vo.AccountName.of(accountName))
                    .accountStatus(accountStatus)
                    .department(department)
                    .version(version)
                    .build();
        }
    }

    public static class DepartmentBuilder {
        private Long id = 1L;
        private String name = "개발팀";

        public DepartmentBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public DepartmentBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Department build() {
            return Department.builder()
                    .id(com.updown.api.department.domain.vo.DepartmentId.of(id))
                    .name(com.updown.api.department.domain.vo.DepartmentName.of(name))
                    .build();
        }
    }

    public static class CreateAccountCommandBuilder {
        private String loginId = "testuser";
        private String password = "password123";
        private String accountName = "테스트사용자";
        private Long departmentId = 1L;

        public CreateAccountCommandBuilder loginId(String loginId) {
            this.loginId = loginId;
            return this;
        }

        public CreateAccountCommandBuilder password(String password) {
            this.password = password;
            return this;
        }

        public CreateAccountCommandBuilder accountName(String accountName) {
            this.accountName = accountName;
            return this;
        }

        public CreateAccountCommandBuilder departmentId(Long departmentId) {
            this.departmentId = departmentId;
            return this;
        }

        public CreateAccountCommand build() {
            return CreateAccountCommand.builder()
                    .loginId(loginId)
                    .password(password)
                    .accountName(accountName)
                    .departmentId(departmentId)
                    .build();
        }
    }

    public static class UpdateAccountCommandBuilder {
        private Long accountId = 1L;
        private String accountName = "수정된이름";

        public UpdateAccountCommandBuilder accountId(Long accountId) {
            this.accountId = accountId;
            return this;
        }

        public UpdateAccountCommandBuilder accountName(String accountName) {
            this.accountName = accountName;
            return this;
        }

        public UpdateAccountCommand build() {
            return UpdateAccountCommand.builder()
                    .accountId(accountId)
                    .accountName(accountName)
                    .build();
        }
    }

    public static class CreateDepartmentCommandBuilder {
        private String name = "개발팀";

        public CreateDepartmentCommandBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CreateDepartmentCommand build() {
            return CreateDepartmentCommand.builder()
                    .name(name)
                    .build();
        }
    }

    public static class UpdateDepartmentCommandBuilder {
        private Long departmentId = 1L;
        private String name = "수정된부서";

        public UpdateDepartmentCommandBuilder departmentId(Long departmentId) {
            this.departmentId = departmentId;
            return this;
        }

        public UpdateDepartmentCommandBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UpdateDepartmentCommand build() {
            return UpdateDepartmentCommand.builder()
                    .departmentId(departmentId)
                    .name(name)
                    .build();
        }
    }

    // 정적 팩토리 메서드들
    public static AccountBuilder account() {
        return new AccountBuilder();
    }

    public static DepartmentBuilder department() {
        return new DepartmentBuilder();
    }

    public static CreateAccountCommandBuilder createAccountCommand() {
        return new CreateAccountCommandBuilder();
    }

    public static UpdateAccountCommandBuilder updateAccountCommand() {
        return new UpdateAccountCommandBuilder();
    }

    public static CreateDepartmentCommandBuilder createDepartmentCommand() {
        return new CreateDepartmentCommandBuilder();
    }

    public static UpdateDepartmentCommandBuilder updateDepartmentCommand() {
        return new UpdateDepartmentCommandBuilder();
    }
}
