package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.config.auth.CustomUserDetails;
import com.ohalfmoon.firework.dto.member.MemberDTO;
import com.ohalfmoon.firework.dto.member.MemberLoginDTO;
import com.ohalfmoon.firework.dto.member.MemberResponseDTO;
import com.ohalfmoon.firework.dto.member.MemberUpdateDTO;
import com.ohalfmoon.firework.service.DeptService;
import com.ohalfmoon.firework.service.MemberService;
import com.ohalfmoon.firework.service.PositionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
 * 2023-06-02        ycy       register 추가
 * 2023-06-05        ycy       login 추가
 * 2023-06-07        ycy       agree 추가
 * 2023-06-08        ycy       register 수정
 * 2023-06-09        ycy       login 수정
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

    String redirect = "redirect:/";

    /**
     * 회원가입 (Get)
     * 가입시 필요한 deptList, positionList 출력
     * @param model the model
     */
    @GetMapping("signup")
    public void register(Model model){
        model.addAttribute("dept", deptService.deptList());
        model.addAttribute("position", positionService.positionList());
        log.info("{}", deptService.deptList());
        log.info("{}", positionService.positionList());
    }


    /**
     * 회원가입(post)
     * 가입 성공시 로그인페이지 이동
     * @param memberDTO the member dto
     * @return the string
     */
    @PostMapping("signup")
    public String register(MemberDTO memberDTO) {
        memberService.register(memberDTO);
        return redirect+"auth/signin";
    }

    @GetMapping("signin")
    public void login() {}

    /**
     * 로그인(post)
     * 로그인 성공시 security session에 로그인정보(MemberLoginDTO) 저장, 메인페이지 이동
     * @param dto     the dto
     * @return the string
     */
    @PostMapping("signin")
    public String login(MemberLoginDTO dto) {
        MemberResponseDTO member = memberService.login(dto);
        if(member == null) {
            return redirect+"auth/signin";
        }else {
            return redirect;
        }

    }


    /**
     * 회원가입 이용약관 페이지
     */
    @GetMapping("agree")
    public void agree() {}

    @GetMapping("agree2")
    public void agree2() {}


    @GetMapping("mypage")
    public void mypage(@AuthenticationPrincipal CustomUserDetails details, Model model) {
        model.addAttribute("user", details);
    }

    /**
     * 회원 정보 확인
     *
     * @param details the details
     * @param model   the model
     */
    @GetMapping("userinfo")
    public void userInfo(@AuthenticationPrincipal CustomUserDetails details, Model model){
        model.addAttribute("user", details);
        log.info("session : {}", model.addAttribute("user", details));
        log.info("session : {}", details);
    }

    /**
     * 회원정보수정 중 기본 회원값 입력
     *
     * @param model   the model
     * @param details the details
     */
    @GetMapping("modify")
    public void modify(Model model, @AuthenticationPrincipal CustomUserDetails details) {
        model.addAttribute("user", details);
        model.addAttribute("dept", deptService.deptList());
        model.addAttribute("position", positionService.positionList());
    }

    /**
     * 회원정보 수정 중 입력한 값을 받아 회원 정보 수정
     *
     * @return login page return
     */
    @PostMapping("modify")
    public String modify(Long userNo, MemberUpdateDTO dto) {
        memberService.update(userNo, dto);
        return redirect+"auth/userinfo";
    }

}
