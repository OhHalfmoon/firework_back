package com.ohalfmoon.firework.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
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
    public void addContextPath(HttpServletRequest request, Model model) {
        String contextPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

        model.addAttribute("contextPath", contextPath);
    }
}
