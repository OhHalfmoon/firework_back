package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.dept.DeptListResponseDTO;
import com.ohalfmoon.firework.dto.member.MemberDTO;
import com.ohalfmoon.firework.dto.member.MemberLoginDTO;
import com.ohalfmoon.firework.dto.member.MemberResponseDTO;
import com.ohalfmoon.firework.service.DeptService;
import com.ohalfmoon.firework.service.MemberService;
import com.ohalfmoon.firework.service.PositionService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private HttpSession session;

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
        return "redirect:/auth/signin";
    }

    @GetMapping("signin")
    public void login() {}

    /**
     * 로그인(post)
     * 로그인 성공시 session에 로그인정보(MemberLoginDTO) 저장, 메인페이지 이동
     * @param dto     the dto
     * @param session the session
     * @return the string
     */
    @PostMapping("signin")
    public String login(@Valid MemberLoginDTO dto, HttpSession session, BindingResult bindingResult, Model model) {
        MemberResponseDTO member = memberService.login(dto);
        String result = "redirect:/";
        if(bindingResult.hasErrors() || member == null) {
            log.info("{}", member);
            model.addAttribute("userDTO", dto);
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put("valid_"+error.getField(), error.getDefaultMessage());
                log.info("error message : {}", error.getDefaultMessage());
            }
            return result+"auth/signin";
        }else {
            session.setAttribute("member", member);
            log.info("session 확인 : {}", session.getAttribute("member"));
            return result;
        }

    }

    /**
     * 회원가입 이용약관 페이지
     */
    @GetMapping("agree")
    public void agree() {}

    @GetMapping("agree2")
    public void agree2() {}

    /**
     * 로그아웃
     *
     * @param session the session
     * @return redirect:/auth/signin
     */
    @GetMapping("signout")
    public String signout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/signin";
    }

    @GetMapping("mypage")
    public void mypage() {}

    @GetMapping("userinfo")
    public void userInfo(@AuthenticationPrincipal MemberLoginDTO memberLoginDTO, Model model, HttpSession session){
        model.addAttribute("user", session.getAttribute("member"));
        log.info("session : {}", session.getAttribute("member"));
    }

    @GetMapping("modify")
    public void modify(Model model) {
        model.addAttribute("user", session.getAttribute("member"));
        model.addAttribute("dept", deptService.deptList());
        model.addAttribute("position", positionService.positionList());
    }

}
