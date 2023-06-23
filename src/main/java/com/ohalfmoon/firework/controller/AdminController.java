package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.member.MemberResponseDTO;
import com.ohalfmoon.firework.dto.member.MemberUpdateStateDTO;
import com.ohalfmoon.firework.model.Role;
import com.ohalfmoon.firework.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
     * 회원 상세페이지 조회
     *
     * @param model the model
     * @param dto   the dto
     * @return the member
     */
    @GetMapping("/member/{userNo}")
    public String getMember(Model model, MemberResponseDTO dto) {
        model.addAttribute("member", memberService.get(dto.getUserNo()));
        return "/admin/member/getMember";
    }

    /**
     * 상태값에 따른 회원 list 출력
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/member")
    public String member(Model model) {
        model.addAttribute("stateByZeroUser", memberService.getStateByZero());
        model.addAttribute("stateByOneUser", memberService.getStateByOne());
        model.addAttribute("stateByTwoUser", memberService.getStateByTwo());
        return "/admin/member/member";
    }
}
