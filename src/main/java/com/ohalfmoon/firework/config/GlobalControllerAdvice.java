package com.ohalfmoon.firework.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * packageName    : com.ohalfmoon.firework.config
 * fileName       : GlobalControllerAdvice
 * author         : banghansol
 * date           : 2023/06/09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/09        banghansol       최초 생성
 */
@ControllerAdvice
public class GlobalControllerAdvice {
    @ModelAttribute("contextPath")
    public String addContextPath(HttpServletRequest request) {
        return request.getContextPath();
    }
}
