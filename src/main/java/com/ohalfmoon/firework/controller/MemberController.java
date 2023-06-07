package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.form.DeptDTO;
import com.ohalfmoon.firework.dto.form.MemberDTO;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@Controller
@RequestMapping("auth")
@Slf4j
public class MemberController {


    @Autowired
    private MemberService memberService;

    @GetMapping("signup")
    public void register(){}


    @PostMapping("signup")
    public String register(MemberDTO memberDTO, DeptDTO deptDTO) {
        memberService.register(memberDTO);
        return "redirect:/auth/signin";
    }

    @GetMapping("signin")
    public void login() {}

    @PostMapping("signin")
    public String login(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        MemberEntity member = memberService.login(username, password);
        HttpSession session = request.getSession();
        session.setAttribute("member", memberService.get(username));
        log.info("{}", session);
        return "redirect:/";
    }

    @GetMapping("agree")
    public void agree() {}
}
