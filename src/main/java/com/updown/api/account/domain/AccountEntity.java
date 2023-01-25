package com.updown.api.account.domain;

import com.updown.api.account.presentation.dto.AccountSaveRequestDTO;
import com.updown.api.account.presentation.dto.AccountUpdateRequestDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String loginId;

    @Column
    private String accountName;

    @Builder
    public AccountEntity(String loginId, String accountName) {
        this.loginId = loginId;
        this.accountName = accountName;
    }

    public static AccountEntity create(AccountSaveRequestDTO accountSaveRequestDTO) {
        return new AccountEntity(accountSaveRequestDTO.getLoginId(), accountSaveRequestDTO.getAccountName());
    }

    public void update(AccountUpdateRequestDTO accountUpdateRequestDTO) {
        this.accountName = accountUpdateRequestDTO.getAccountName();
    }
}
