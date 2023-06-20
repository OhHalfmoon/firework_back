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

/**
 * packageName    : com.ohalfmoon.firework.controller
 * fileName       : AttendController
 * author         : 이지윤
 * date           : 2023/06/16
 * description    : 근태(출근, 퇴근 등록) 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/16        이지윤            최초 생성
 * 2023/06/19        이지윤            getAttendNo() 추가
 */

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

    @GetMapping("/{userNo}")
    public Long getAttendNo(@AuthenticationPrincipal CustomUserDetails user, @PathVariable Long userNo) {
        Long attendNo = attendService.getAttendNo(user.getUserNo());
        return attendNo;
    }

    @PutMapping("/{attendNo}")
    public void leaveWork(@RequestBody AttendUpdateDTO attendUpdateDTO) {
        attendService.updateAttend(attendUpdateDTO);

    }
}



