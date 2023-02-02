package com.updown.api.common.security;

import com.updown.api.account.domain.AccountEntity;
import com.updown.api.account.infrastructure.repository.AccountEntityRepository;
import com.updown.api.common.exception.CustomRuntimeException;
import com.updown.api.common.exception.ExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpringSecurityUserDetailsServiceImpl implements UserDetailsService {

    private final AccountEntityRepository accountEntityRepository;

    // Spring Security UserDetailsService interface implements 받아서 loadUserByUserName메소드 오버라이드 하면 로그인 시에 여기로 들어와서 체크
    // 그 다음 여기에 디비 조회해서 가져오도록 하면 로그인 기능 구현 가능
    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

        // 이쪽에 디비에 만들어놓음 사용자 아이디 조회해서 있는지 없는지 보고 SpringSecurity UserDetails implements 받아서 메소드 오버라이드 해놓은
        // 클래스 인스턴스화 해서 리턴하면 클라이언트의 쿠키에 JSESSIONID라는 키로 SessionID가 저장되기 때문에 한 브라우져 안에서 인증은 계속 유효하고
        // 브라우져 끄거나 하면 다시 받아야함
        AccountEntity accountEntity = accountEntityRepository.findAccountByLoginId(loginId)
                .orElseThrow(() -> new CustomRuntimeException(ExceptionType.NOT_FOUND_USER));

        return new SpringSecurityUserDetailsImpl(accountEntity);
    }
}
