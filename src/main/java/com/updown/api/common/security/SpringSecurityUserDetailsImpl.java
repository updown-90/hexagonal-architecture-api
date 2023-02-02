package com.updown.api.common.security;

import com.updown.api.account.domain.AccountEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
public class SpringSecurityUserDetailsImpl implements UserDetails {
    private AccountEntity accountEntity;

    // User Entity가 가지고 있는 권한 목록을 저장하여 리턴한다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 권한 목록을 저장할 컬렉션
        Collection<GrantedAuthority> roleList = new ArrayList<>();

        // 권한 설정
        roleList.add((GrantedAuthority) () -> "ROLE_" + accountEntity.getAccountName());

        return roleList;
    }
    @Override
    public String getPassword() {
        return accountEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return accountEntity.getAccountName();
    }

    // 계정이 만료됐는지 여부를 리턴한다.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겨있는지 여부를 리턴한다.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호가 만료됐는지 여부를 리턴한다.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정의 활성화 여부를 리턴한다.
    @Override
    public boolean isEnabled() {
        return true;
    }


}
