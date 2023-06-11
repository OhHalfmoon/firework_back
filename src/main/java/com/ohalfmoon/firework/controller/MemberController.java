package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.dept.DeptListResponseDTO;
import com.ohalfmoon.firework.dto.member.MemberDTO;
import com.ohalfmoon.firework.dto.member.MemberLoginDTO;
import com.ohalfmoon.firework.dto.member.MemberResponseDTO;
import com.ohalfmoon.firework.service.DeptService;
import com.ohalfmoon.firework.service.MemberService;
import com.ohalfmoon.firework.service.PositionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private DeptService deptService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private HttpSession session;

    @GetMapping("signup")
    public void register(Model model){
        model.addAttribute("dept", deptService.deptList());
        model.addAttribute("position", positionService.positionList());
        log.info("{}", deptService.deptList());
        log.info("{}", positionService.positionList());
    }


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
