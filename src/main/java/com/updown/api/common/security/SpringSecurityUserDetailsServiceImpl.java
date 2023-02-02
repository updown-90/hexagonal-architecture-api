package com.updown.api.common.security;

import com.updown.api.account.domain.AccountEntity;
import com.updown.api.account.infrastructure.repository.AccountEntityRepository;
import com.updown.api.common.exception.CustomRuntimeException;
import com.updown.api.common.exception.ExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpringSecurityUserDetailsServiceImpl implements UserDetailsService {

    private final AccountEntityRepository accountEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        AccountEntity accountEntity = accountEntityRepository.findAccountByLoginId(loginId)
                .orElseThrow(() -> new CustomRuntimeException(ExceptionType.NOT_FOUND_USER));

        return new SpringSecurityUserDetailsImpl(accountEntity);
    }
}
