package com.updown.api.common.security;

import com.updown.api.account.domain.AccountEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// spring Security에서 제공하는 사용자 Interface로 UserDetails 가 있는데 이를 구현해 놓은 구현체로 User Class가 있다
// UserDetails Interface 를 직접 구현하거나 User Class를 확장해서 사용하면 된다.
public class SpringSecurityUserDetailsImpl extends User {

    public SpringSecurityUserDetailsImpl(AccountEntity accountEntity) {
        super(accountEntity.getLoginId(), accountEntity.getPassword(), authorities(accountEntity));
    }

    private static Collection<? extends GrantedAuthority> authorities(AccountEntity accountEntity) {
        List<GrantedAuthority> authorities = new ArrayList<>();
//        if (accountEntity.isAdmin()) {
//            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        } else {
//            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//        }
        // Spring Security가 앞에 ROLE_ 붙여야 인식됨
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }


}
