package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.MemberDTO;
import com.ohalfmoon.firework.model.DeptEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("auth")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("signup")
    public void register(){}

    @PostMapping("signup")
    public String register(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.builder()
                .username(memberDTO.getUsername())
                .password(memberDTO.getPassword())
                .email(memberDTO.getEmail())
                .phoneNum(memberDTO.getPhoneNum())
                .name(memberDTO.getName())
                .startdate(memberDTO.getStartdate())
                .birthdate(memberDTO.getBirthdate())
                .startdate(memberDTO.getStartdate())
//                .deptEntity(memberDTO.getDeptNo())
                .build();
        MemberEntity registerMember =  memberService.register(memberEntity);
        MemberDTO responseDTO = MemberDTO.builder()
                .username(registerMember.getUsername())
                .build();
        return "redirect:/login";
    }

    @GetMapping("signin")
    public void login() {}

    @PostMapping("signin")
    public String login(MemberDTO memberDTO) {
        return "redirect:/";
    }
}
