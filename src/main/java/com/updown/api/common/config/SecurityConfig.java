package com.updown.api.common.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();

        http
                .csrf().disable()
                .authorizeRequests() // 요청에 의한 보안검사 시작
                .antMatchers("/**")
//                .anyRequest().authenticated() //어떤 요청에도 보안검사를 한다.
                .permitAll(); //어떤 요청에도 보안검사를 한다.
    }

}
