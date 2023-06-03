package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.MemberDTO;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auth")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("signup")
    public void register(){}

    @PostMapping("signup")
    public String register(MemberDTO memberDTO) {
        memberService.register(memberDTO.toEntity());
        return "redirect:/";
    }
}
