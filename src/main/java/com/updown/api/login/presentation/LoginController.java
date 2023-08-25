package com.updown.api.login.presentation;

import com.updown.api.account.domain.AccountEntity;
import com.updown.api.account.infrastructure.repository.AccountEntityRepository;
import com.updown.api.common.exception.CustomRuntimeException;
import com.updown.api.common.exception.ExceptionType;
import com.updown.api.common.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final AccountEntityRepository accountEntityRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @GetMapping("/login")
    public String login(String loginId, String password) {
        AccountEntity accountEntity = accountEntityRepository.findAccountByLoginId(loginId)
                .orElseThrow(() -> new CustomRuntimeException(ExceptionType.NOT_FOUND_USER));

        if (!passwordEncoder.matches(password, accountEntity.getPassword())) {
            throw new CustomRuntimeException(ExceptionType.NOT_FOUND_USER);
        }

        return jwtProvider.createToken(accountEntity.getLoginId(), List.of("USER"));
    }
}
