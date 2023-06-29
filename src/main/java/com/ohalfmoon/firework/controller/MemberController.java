package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.config.auth.CheckUsernameValidator;
import com.ohalfmoon.firework.config.auth.CustomUserDetails;
import com.ohalfmoon.firework.config.auth.CustomUserDetailsService;
import com.ohalfmoon.firework.dto.member.*;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.persistence.MemberRepository;
import com.ohalfmoon.firework.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

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
 * 2023-06-20        ycy       id중복체크, 회원가입시 유효성검사 코드 작성
 */
//@Controller
@Controller
@RequestMapping("auth")
@Slf4j
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;

    private final MemberService memberService;

    private final DeptService deptService;

    private final PositionService positionService;

    private final CheckUsernameValidator checkUsernameValidator;

    private final AttendService attendService;

    private final BoardService boardService;

    private final CustomUserDetailsService customUserDetailsService;

    private final PasswordEncoder encoder;

    String redirect = "redirect:/";

    /**
     * id 중복체크 메서드
     * MemberDTO를 사용할때만 중복체크
     * @param binder the binder
     */
    @InitBinder("MemberDTO")
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkUsernameValidator);
    }

    @GetMapping("auth/signup/{username}/exists")
        public ResponseEntity<Boolean> checkUsernameDuplicate (@PathVariable String username) {
            return ResponseEntity.ok(memberService.checkUsernameDuplication(username));
    }


    /**
     * 회원가입 (Get)
     * 가입시 필요한 deptList, positionList 출력
     * @param model the model
     */
    @GetMapping("signup")
    public void register(Model model){
        model.addAttribute("dept", deptService.deptList());
        model.addAttribute("position", positionService.positionList());
    }

    /**
     * 회원가입(post)
     * 가입 성공시 로그인페이지 이동
     * @param memberDTO the member dto
     * @return the string
     */
    @PostMapping("signupProc")
    public String register(@Valid MemberDTO memberDTO, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("member", memberDTO);
            model.addAttribute("dept", deptService.deptList());
            model.addAttribute("position", positionService.positionList());

            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put("valid_"+error.getField(), error.getDefaultMessage());
                log.info("error message controller : "+error.getDefaultMessage());
            }

            for(String key : errorMap.keySet()) {
                model.addAttribute(key, errorMap.get(key));
            }
            model.addAttribute("errorMap", errorMap);
            // 회원가입 실패시 회원가입 페이지로 리턴
            return "/auth/signup";
        }
        // 회원가입 성공시 로그인 페이지로 리턴
        memberService.register(memberDTO);
        return redirect+"auth/signin";
    }

    /**
     * 로그인실패시 에러메세지 출력
     *
     * @param error     the error
     * @param exception the exception
     * @param model     the model
     */
    @GetMapping("signin")
    public void login(@RequestParam(value = "error", required = false)String error, @RequestParam(value = "exception", required = false)String exception, Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
    }

    /**
     * 회원가입 이용약관 페이지
     */

    @GetMapping("agree2")
    public void agree2() {}

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
     * 회원정보 수정 후 session 재설정
     *
     * @return login page return
     */
    @PostMapping("modify")
    public String modify(Long userNo, MemberUpdateDTO dto, HttpSession session) {
        memberService.update(userNo, dto);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // session값 가져옴
        CustomUserDetails details = (CustomUserDetails) customUserDetailsService.loadUserByUsername(authentication.getName()); // 현재 사용자의 정보를 CustomUserDetails에 담음?
        UsernamePasswordAuthenticationToken passwordAuthenticationToken = new UsernamePasswordAuthenticationToken(
            details, authentication.getCredentials(), details.getAuthorities()); // session값 설정
        SecurityContextHolder.getContext().setAuthentication(passwordAuthenticationToken); // 인증 토큰 설정
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, // 세션 저장
                SecurityContextHolder.getContext());

        return redirect+"auth/userinfo";
    }
    @GetMapping("modifyPw")
    public void modifyPw(Model model, @AuthenticationPrincipal CustomUserDetails details) {
        model.addAttribute("user", details);
        model.addAttribute("dept", deptService.deptList());
        model.addAttribute("position", positionService.positionList());
    }

    /**
     * 비밀번호 수정
     *
     * @param userNo  the user no
     * @param dto     the dto
     * @param session the session
     * @return the string
     */
    @PostMapping("modifyPw")
    public String modifyPw(Long userNo, MemberUpdatePwDTO dto, HttpSession session, Model model) {
        if (memberService.updatePw(userNo, dto).equals("success")) {
            memberService.updatePw(userNo, dto);
            session.invalidate();
            return redirect+"auth/signin";
        }
        else {
            model.addAttribute("message", "기존 비밀번호와 다릅니다. 다시 입력해주세요");
            return "auth/modifyPw";
        }
    }


    /**
     * 근태 확인
     *
     * @param model the model
     * @param dto   the dto
     * @return the attend
     */
    @GetMapping("getAttend/{userNo}")
    public String getAttend(Model model, MemberResponseDTO dto) {
        model.addAttribute("attend", attendService.getAttend(dto.getUserNo()));
        return "/auth/getAttend";
    }
}
