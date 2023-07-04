package com.ohalfmoon.firework.config;

import com.ohalfmoon.firework.config.auth.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * packageName    : com.ohalfmoon.firework.config
 * fileName       : GlobalControllerAdvice
 * author         : banghansol
 * date           : 2023/06/09
 * description    : 전역 변수 관리 클래스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/09        banghansol       최초 생성
 * 2023/06/21        banghansol       로그인 정보 출력용
 */
@ControllerAdvice
@Slf4j
public class GlobalControllerAdvice {
    @ModelAttribute("contextPath")
    public void addContextPath(HttpServletRequest request, Model model) {
        String contextPath = request.getScheme() + "://" + request.getServerName();

        int serverPort = request.getServerPort();
        if (serverPort != 80 && serverPort != 443) {
            contextPath += ":" + serverPort;
        }

        model.addAttribute("contextPath", contextPath);

    }

    @ModelAttribute("user")
    public UserDetails getCurrentsUser(Authentication authentication) {
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (CustomUserDetails) authentication.getPrincipal();
        }
        return null;
    }
}
