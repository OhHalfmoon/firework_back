package com.ohalfmoon.firework.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * packageName :  com.ohalfmoon.firework.config
 * fileName : WebSecurityConfig
 * author :  ycy
 * date : 2023-06-07
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-07                ycy             최초 생성
 */
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and().csrf().disable()
                .formLogin().loginPage("/auth/signin") // 커스텀 로그인폼 사용
                .permitAll() // 권한없이 사용
                .and().httpBasic()
//                .httpBasic().disable() // 기본 로그인폼 사용안함
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // session방식 사용
//                .and().authorizeRequests()
//                .antMatchers("/auth/login", "/auth/signup").permitAll() // 로그인, 회원가입 페이지는 권한이 없어도 사용 가능
//                .antMatchers("/").hasRole(Role.EMPLOYEE.name()) // 그 외 모든페이지는 최소 USER권한이 있어야만 사용가능
                .and().logout().logoutSuccessUrl("/auth/signin");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
