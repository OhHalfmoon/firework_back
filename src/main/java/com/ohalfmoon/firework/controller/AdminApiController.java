package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.attend.AttendUpdateByAdminDTO;
import com.ohalfmoon.firework.dto.member.MemberUpdateStateDTO;
import com.ohalfmoon.firework.service.AttendService;
import com.ohalfmoon.firework.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    private final AttendService attendService;

    /**
     * 회원가입 승인
     *
     * @param dtos the dtos
     * @return the response entity
     */
    @PostMapping("/memberUpdateBulk")
    public ResponseEntity<?> updateState(@RequestBody List<MemberUpdateStateDTO> dtos) {
        String result = "error";
        for(MemberUpdateStateDTO item : dtos){
            if(item != null) {
                memberService.recognize(item.getUserNo(), item);
                result = "success";
            }
        }
        return ResponseEntity.ok().body(result);
    }

    /**
     * 회원 근태 수정
     *
     * @param dto the dto
     * @return the response entity
     */
    @PostMapping("/member/attendUpdate")
    public ResponseEntity<?> updateAttend(@RequestBody AttendUpdateByAdminDTO dto) {
        String result = "error";
        log.info("dto 확인 : {}", dto);
        if(dto != null) {
            attendService.updateByAdmin(dto.getAttendNo(), dto);
            result = "success";
        }
        return ResponseEntity.ok().body(result);
    }

    /**
     * 회원 근태 삭제
     *
     * @param attendNo the attend no
     * @return the response entity
     */
    @DeleteMapping("/member/delete/{attendNo}")
    public ResponseEntity<?> deleteAttend(@PathVariable Long attendNo) {
        String result= "error";
        if(attendNo != null) {
            attendService.deleteByAdmin(attendNo);
            result = "success";
        }
        return ResponseEntity.ok().body(result);
    }
}
