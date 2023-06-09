package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.AlarmResponseDto;
import com.ohalfmoon.firework.dto.AlarmSaveDto;
import com.ohalfmoon.firework.dto.member.MemberDTO;
import com.ohalfmoon.firework.service.AlarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName    : com.ohalfmoon.firework.controller
 * fileName       : AlarmApiController
 * author         : 우성준
 * date           : 2023/06/08
 * description    : 알림 API 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/08        우성준            최초 생성
 */
@RequiredArgsConstructor
@RequestMapping("/api/alarm")
@RestController
public class AlarmApiController {
    private final AlarmService alarmService;

    @GetMapping("/{userNo}")
    public List<AlarmResponseDto> findAllByAlarmReceiver(@PathVariable Long userNo) {
        return alarmService.findAllByAlarmReceiver(userNo);
    }

    @PostMapping("/")
    public Long save(@RequestBody AlarmSaveDto alarmSaveDto) {
        return alarmService.save(alarmSaveDto);
    }
}
