package com.ohalfmoon.firework.config;

import com.ohalfmoon.firework.config.auth.CustomAuthFailureHandler;
import com.ohalfmoon.firework.config.auth.CustomAuthSuccessHandler;
import com.ohalfmoon.firework.config.auth.CustomUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
 * 2023-06-13                ycy             security 적용완료
 * 2023-06-20                ycy             로그인 실패 핸들러 적용
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService customUserDetailsService;

    private final CustomAuthFailureHandler customFailureHandler;

    private final CustomAuthSuccessHandler customSuccessHandler;

    public WebSecurityConfig(CustomUserDetailsService customUserDetailsService
            , CustomAuthFailureHandler customFailureHandler
            , CustomAuthSuccessHandler customSuccessHandler
    ) {
        this.customUserDetailsService = customUserDetailsService;
        this.customFailureHandler = customFailureHandler;
        this.customSuccessHandler = customSuccessHandler;
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
                .defaultSuccessUrl("/", true)
                .successHandler(customSuccessHandler) // login 성공 핸들러
                .failureHandler(customFailureHandler) // login 실패 핸들러
                .and().httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // session방식 사용
                // 권한관련 시작 (배포 전까지 주석)
                .and().authorizeRequests() // 인증절차에 대한 설정을 진행
                .antMatchers("/auth/signin", "/auth/signup", "/auth/agree2").permitAll() // 로그인, 회원가입 페이지는 권한이 없어도 사용 가능
                .antMatchers("/").hasAnyRole("EMPLOYEE", "TL", "CEO", "ADMIN") // 그 외 페이지는 인증된 사람만 이용가능
                .antMatchers("/admin/**").hasRole("ADMIN") // 관리자페이지 권한설정
                .antMatchers("/dist/**", "/plugins/**", "/resources/**").permitAll() // 정적파일 호출
                .anyRequest().authenticated()
                // 권한관련 끝 (배포 전까지 주석)
                .and().logout().logoutSuccessUrl("/auth/signin"); // logout시 이동
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    public void configure(WebSecurity web) throws Exception { // status:999 error 제거
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());

    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


}
