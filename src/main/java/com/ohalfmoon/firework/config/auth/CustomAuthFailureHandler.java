package com.ohalfmoon.firework.config.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * packageName :  com.ohalfmoon.firework.config.auth
 * fileName : CustomAuthFailureHandler
 * author :  ycy
 * date : 2023-06-20
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-20                ycy             최초 생성
 */
@Component
@Slf4j
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = "";
        if(exception instanceof BadCredentialsException) { // 비밀번호가 일치하지 않는경우
            errorMessage = "아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해 주세요.";
        } else if (exception instanceof InternalAuthenticationServiceException) { // 시스템 문제로 내부 인증 관련 처리 요청을 할 수 없는경우
            errorMessage = "탈퇴하셨거나 미인증 유저 입니다. 관리자에게 문의하세요";
        } else if (exception instanceof UsernameNotFoundException) { // 
            errorMessage = "존재하지 않는 id 입니다. 회원가입 진행 후 로그인해주세요";
            log.info(errorMessage);
        } else if (exception instanceof AuthenticationCredentialsNotFoundException) { // 인증 요구가 거부 됐을 경우
            errorMessage = "인증요청이 거부되었습니다. 관리자에게 문의하세요";
        } else {
            errorMessage = "알 수 없는 문제가 발생하였습니다. 관리자에게 문의하세요";
        }
        errorMessage = URLEncoder.encode(errorMessage, "UTF-8"); // 한글 깨짐문제 해결
        setDefaultFailureUrl("/auth/signin?error=true&exception="+errorMessage);
        super.onAuthenticationFailure(request, response, exception);
    }
}
