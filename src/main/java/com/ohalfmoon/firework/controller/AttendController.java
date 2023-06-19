package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.config.auth.CustomUserDetails;
import com.ohalfmoon.firework.dto.attend.AttendSaveDTO;
import com.ohalfmoon.firework.dto.attend.AttendUpdateDTO;
import com.ohalfmoon.firework.service.AttendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("attend")
@Slf4j
public class AttendController {
    @Autowired
    private AttendService attendService;

    @PostMapping("/{userNo}")
    public void gotoWork(@AuthenticationPrincipal CustomUserDetails user, @RequestBody AttendSaveDTO attendSaveDTO) {
        attendSaveDTO.setUserNo(user.getUserNo());
        attendService.save(attendSaveDTO);
    }

    @PutMapping("/{attendNo}")
    public void leaveWork(@RequestBody AttendUpdateDTO attendUpdateDTO, @PathVariable Long attendNo) {
        // attendNo, leavedate
        log.info("attendNo : {}", attendNo);
        attendService.updateAttend(attendNo, attendUpdateDTO);
    }
}



