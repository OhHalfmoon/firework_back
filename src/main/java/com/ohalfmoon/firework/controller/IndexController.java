package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.config.auth.CustomUserDetails;
import com.ohalfmoon.firework.dto.attend.AttendSaveDTO;
import com.ohalfmoon.firework.dto.member.MemberLoginDTO;
import com.ohalfmoon.firework.dto.member.MemberResponseDTO;
import com.ohalfmoon.firework.service.AttendService;
import com.ohalfmoon.firework.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * packageName    : com.ohalfmoon.firework.controller
 * fileName       : IndexController
 * author         : banghansol
 * date           : 2023/06/01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/01        banghansol       최초 생성
 */
@Controller("/")
@Slf4j
public class IndexController {
    @Autowired
    MemberService memberService;

    @Autowired
    private AttendService attendService;

    @GetMapping
    public String index(HttpSession session, @AuthenticationPrincipal CustomUserDetails details, Model model, HttpServletRequest req) {
        model.addAttribute("user", details);
        log.info("security session : {}", model.getAttribute("user"));
        log.info("session :{}", session.getAttribute("member"));
        return "index";
    }
    @GetMapping("login")
    public String login() {
        return "login";
    }

}