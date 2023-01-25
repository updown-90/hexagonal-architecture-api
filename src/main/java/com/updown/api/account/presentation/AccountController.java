package com.updown.api.account.presentation;

import com.updown.api.account.domain.AccountEntity;
import com.updown.api.account.presentation.dto.AccountSaveRequestDTO;
import com.updown.api.account.presentation.dto.AccountUpdateRequestDTO;
import com.updown.api.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @ResponseBody 알아서 사용하게 해주려고 사용
@RequiredArgsConstructor // @RequiredArgsConstructor는 초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성해 줍니다.
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/account")
    public AccountEntity createAccount(@RequestBody AccountSaveRequestDTO accountSaveRequestDTO) {
        return accountService.createAccount(accountSaveRequestDTO);
    }

    @GetMapping("/account")
    public List<AccountEntity> findAllAccount() {
        return accountService.findAllAccount();
    }

    @GetMapping("/account/{id}")
    public AccountEntity findAccountById(@PathVariable Long id) {
        return accountService.findAccountById(id);
    }

    @PutMapping("/account")
    public AccountEntity updateAccount(@RequestBody AccountUpdateRequestDTO accountUpdateRequestDTO) throws Exception {
        return accountService.updateAccount(accountUpdateRequestDTO);
    }

    @DeleteMapping("/account/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }

}
