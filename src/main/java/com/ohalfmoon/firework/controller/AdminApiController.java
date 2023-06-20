package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.member.MemberUpdateStateDTO;
import com.ohalfmoon.firework.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * packageName :  com.ohalfmoon.firework.controller
 * fileName : AdminController
 * author :  ycy
 * date : 2023-06-19
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-19                ycy             최초 생성
 */
@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
@Slf4j
public class AdminApiController {

    private final MemberService memberService;

    @PostMapping("/member/memberUpdate/{userNo}")
    public Long updateState(@PathVariable Long userNo, @RequestBody MemberUpdateStateDTO dto) {
        Long update = memberService.recognize(userNo, dto);
        return update;
    }
}
