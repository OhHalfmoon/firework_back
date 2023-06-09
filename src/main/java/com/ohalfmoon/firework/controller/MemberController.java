package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.member.MemberDTO;
import com.ohalfmoon.firework.dto.member.MemberLoginDTO;
import com.ohalfmoon.firework.dto.member.MemberResponseDTO;
import com.ohalfmoon.firework.dto.role.RoleDTO;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * packageName    : com.ohalfmoon.firework.controller
 * fileName       : MemberController
 * author         : ycy
 * date           : 2023/06/01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/01        ycy       최초 생성
 */
//@Controller
@Controller
@RequestMapping("auth")
@Slf4j
public class MemberController {


    @Autowired
    private MemberService memberService;

    @Autowired
    private HttpSession session;

    @GetMapping("signup")
    public void register(){}


    @PostMapping("signup")
    public String register(MemberDTO memberDTO) {
        memberService.register(memberDTO);
        return "redirect:/auth/signin";
    }

    @GetMapping("signin")
    public void login() {}

    @PostMapping("signin")
    public String login(MemberLoginDTO dto, HttpSession session) {
        MemberResponseDTO member = memberService.login(dto);
        if(member != null) {
            session.setAttribute("member", member);
            log.info("{} session 확인 :", session.getAttribute("member"));
            return "redirect:/";

        }else {
            return "redirect:/auth/signin";
        }

    }

    @GetMapping("agree")
    public void agree() {}
}
