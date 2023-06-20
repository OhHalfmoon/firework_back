package com.ohalfmoon.firework.config;

import com.ohalfmoon.firework.config.auth.CustomAuthFailureHandler;
import com.ohalfmoon.firework.config.auth.CustomUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

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
 * 2023-06-13                ycy             security 적용완료
 * 2023-06-20                ycy             로그인 실패 핸들러 적용
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService customUserDetailsService;

    private final CustomAuthFailureHandler customFailureHandler;

    public WebSecurityConfig(CustomUserDetailsService customUserDetailsService, CustomAuthFailureHandler customFailureHandler) {
        this.customUserDetailsService = customUserDetailsService;
        this.customFailureHandler = customFailureHandler;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and().csrf().disable()
                .formLogin().loginPage("/auth/signin") // 커스텀 로그인폼 사용
                .failureHandler(customFailureHandler) // login 실패 핸들러
                .and().httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // session방식 사용
                // 배포 전까지 주석 시작
//                .and().authorizeRequests() // 인증절차에 대한 설정을 진행
//                .antMatchers("/auth/signin", "/auth/signup", "/auth/agree").permitAll() // 로그인, 회원가입 페이지는 권한이 없어도 사용 가능
//                .antMatchers("/").hasAnyRole("EMPLOYEE", "TL", "CEO", "ADMIN") // 그 외 페이지는 인증된 사람만 이용가능
//                .antMatchers("/dist/**", "/plugins/**").permitAll() // 정적파일 호출
//                .anyRequest().authenticated()
                // 배포 전까지 주석 끝
                .and().logout().logoutSuccessUrl("/auth/signin"); // logout시 이동
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

}
