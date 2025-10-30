package com.updown.api.account.adapter.in.web;

import com.updown.api.account.application.port.in.CreateAccountInPort;
import com.updown.api.account.application.port.in.GetAccountInPort;
import com.updown.api.account.application.port.in.UpdateAccountInPort;
import com.updown.api.account.application.port.in.DeleteAccountInPort;
import com.updown.api.account.application.port.in.dto.CreateAccountCommand;
import com.updown.api.account.application.port.in.dto.UpdateAccountCommand;
import com.updown.api.account.application.port.in.dto.AccountResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 계정 웹 컨트롤러
 */
@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    
    private final CreateAccountInPort createAccountInPort;
    private final GetAccountInPort getAccountInPort;
    private final UpdateAccountInPort updateAccountInPort;
    private final DeleteAccountInPort deleteAccountInPort;
    
    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@RequestBody @Valid CreateAccountCommand command) {
        AccountResponse response = createAccountInPort.createAccount(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable Long accountId) {
        AccountResponse response = getAccountInPort.getAccount(accountId);
        return ResponseEntity.ok(response);
    }
    
    @PutMapping
    public ResponseEntity<AccountResponse> updateAccount(@RequestBody @Valid UpdateAccountCommand command) {
        AccountResponse response = updateAccountInPort.updateAccount(command);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId) {
        deleteAccountInPort.deleteAccount(accountId);
        return ResponseEntity.noContent().build();
    }
}
