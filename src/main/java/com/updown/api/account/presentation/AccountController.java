package com.updown.api.account.presentation;

import com.updown.api.account.infrastructure.dto.AccountEntityQueryDSLResponse;
import com.updown.api.account.presentation.dto.request.AccountSaveRequest;
import com.updown.api.account.presentation.dto.request.AccountUpdateRequest;
import com.updown.api.account.presentation.dto.request.AccountsFindRequest;
import com.updown.api.account.presentation.dto.response.AccountFindResponse;
import com.updown.api.account.presentation.dto.response.AccountSaveResponse;
import com.updown.api.account.presentation.dto.response.AccountUpdateResponse;
import com.updown.api.account.presentation.mapstruct.mapper.AccountEntityMapper;
import com.updown.api.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController // @ResponseBody 알아서 사용하게 해주려고 사용
@RequiredArgsConstructor // @RequiredArgsConstructor는 초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성해 줍니다.
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

//    @ResponseStatus(HttpStatus.CREATED)
//    @Logger 해당 어노테이션이 있으면 AOP 동작하도록 개발되어 있음
    @PostMapping
    public AccountSaveResponse createAccount(@RequestBody @Valid AccountSaveRequest accountSaveRequest) {
        return AccountEntityMapper.INSTANCE.accountEntityToAccountSaveResponseDTO(
                accountService.createAccount(accountSaveRequest)
        );
    }

    @GetMapping
    public List<AccountEntityQueryDSLResponse> findAccounts(AccountsFindRequest accountsFindRequest) {
        return accountService.findAccounts(accountsFindRequest);
    }

    @GetMapping("/{id}")
    public AccountFindResponse findAccountById(@PathVariable Long id) {
        return AccountEntityMapper.INSTANCE.accountEntityToAccountFindResponseDTO(
                accountService.findAccountById(id)
        );
    }

    @PutMapping
    public AccountUpdateResponse updateAccount(@RequestBody AccountUpdateRequest accountUpdateRequest) {
        return AccountEntityMapper.INSTANCE.accountEntityToAccountUpdateResponseDTO(
                accountService.updateAccount(accountUpdateRequest)
        );
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }

}
