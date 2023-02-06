package com.updown.api.common.config;

import com.updown.api.common.security.SpringSecurityUserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity // 기본적인 스큐리티 설정을 사용할거다.
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final SpringSecurityUserDetailsServiceImpl springSecurityUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .formLogin() // 기본 로그인 화면 사용하는 설정

            .and()
            .csrf().disable() // token을 사용하는 방식이기 때문에 csrf를 disable합니다.

            .headers().frameOptions().sameOrigin() // h2-console 사용하기 위한 설정

            .and()
            .authorizeRequests() // HttpServletRequest를 사용하는 요청들에 대한 접근제한을 설정하겠다.
            .antMatchers("/**").permitAll();
//            .antMatchers(HttpMethod.POST, "/account").permitAll() // 해당 패턴에 해당하는 것은 인증 허용
//            .antMatchers("/", "/h2-console/**", "/account/create").permitAll() // 해당 패턴에 해당하는 것은 인증 허용
//            .anyRequest().authenticated(); // 나너지 요청들에 대해서는 인증을 받아야한다.
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(springSecurityUserDetailsService);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
