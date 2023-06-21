package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.member.MemberUpdateStateDTO;
import com.ohalfmoon.firework.model.Role;
import com.ohalfmoon.firework.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName :  com.ohalfmoon.firework.controller
 * fileName : AdminController
 * author :  ycy
 * date : 2023-06-15
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-15                ycy             최초 생성
 */
@Controller
@RequestMapping("admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final MemberService memberService;

    @GetMapping
    public String admin() {
        return "/admin/index";
    }

    /**
     * (관리자)회원가입 승인 페이지
     *
     * @param model the model
     */
    @GetMapping("/member/memberUpdate")
    public void updateState(Model model) {
        model.addAttribute("stateByZeroUser", memberService.getStateByZero());
//        model.addAttribute("role", Role.values());
    }

//    @PostMapping("/member/memberUpdate")
//    public String updateState(MemberUpdateStateDTO dto) {
//        memberService.recognize(dto.getUserNo(), dto);
//        return "redirect:/admin/member/memberUpdate";
//    }
}
