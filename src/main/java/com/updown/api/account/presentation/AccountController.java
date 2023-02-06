package com.updown.api.account.presentation;

import com.updown.api.account.infrastructure.dto.AccountEntityQueryDSLDTO;
import com.updown.api.account.presentation.dto.request.AccountSaveRequestDTO;
import com.updown.api.account.presentation.dto.request.AccountUpdateRequestDTO;
import com.updown.api.account.presentation.dto.request.AccountsFindRequestDTO;
import com.updown.api.account.presentation.dto.response.AccountFindResponseDTO;
import com.updown.api.account.presentation.dto.response.AccountSaveResponseDTO;
import com.updown.api.account.presentation.dto.response.AccountUpdateResponseDTO;
import com.updown.api.account.presentation.mapstruct.mapper.AccountEntityMapper;
import com.updown.api.account.service.AccountService;
import com.updown.api.common.aop.Logger;
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
    @PostMapping
    @Logger
    public AccountSaveResponseDTO createAccount(@RequestBody @Valid AccountSaveRequestDTO accountSaveRequestDTO) {
        return AccountEntityMapper.INSTANCE.accountEntityToAccountSaveResponseDTO(
                accountService.createAccount(accountSaveRequestDTO)
        );
    }

    @GetMapping
    public List<AccountEntityQueryDSLDTO> findAccounts(AccountsFindRequestDTO accountsFindRequestDTO) {
        return accountService.findAccounts(accountsFindRequestDTO);
    }

    @GetMapping("/{id}")
    public AccountFindResponseDTO findAccountById(@PathVariable Long id) {
        return AccountEntityMapper.INSTANCE.accountEntityToAccountFindResponseDTO(
                accountService.findAccountById(id)
        );
    }

    @PutMapping
    public AccountUpdateResponseDTO updateAccount(@RequestBody AccountUpdateRequestDTO accountUpdateRequestDTO) {
        return AccountEntityMapper.INSTANCE.accountEntityToAccountUpdateResponseDTO(
                accountService.updateAccount(accountUpdateRequestDTO)
        );
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }

}
