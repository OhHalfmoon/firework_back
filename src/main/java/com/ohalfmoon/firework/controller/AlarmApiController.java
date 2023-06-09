package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.AlarmResponseDto;
import com.ohalfmoon.firework.dto.AlarmSaveDto;
import com.ohalfmoon.firework.dto.member.MemberDTO;
import com.ohalfmoon.firework.service.AlarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
 * 2023/06/09        우성준            CRUD 작업
 */
@RequiredArgsConstructor
@RequestMapping("/api/alarm")
@RestController
@Slf4j
public class AlarmApiController {
    private final AlarmService alarmService;

    @GetMapping("/{userNo}")
    public List<AlarmResponseDto> findAllByAlarmReceiver(@PathVariable("userNo") Long userNo) {
        return alarmService.findAllByAlarmReceiver(userNo);
    }

    @PostMapping("/")
    public Long save(@RequestBody AlarmSaveDto alarmSaveDto) {
        return alarmService.save(alarmSaveDto);
    }

    @PutMapping("/{alarmNo}")
    public Long update(@PathVariable Long alarmNo, @RequestBody Map<String,  Boolean> map) {
        return alarmService.update(alarmNo, map.get("alarmCheck"));
    }

    @DeleteMapping("/{alarmNo}")
    public Long delete(@PathVariable Long alarmNo) {
        alarmService.delete(alarmNo);
        return alarmNo;
    }

    @GetMapping("/{alarmNo}")
    public AlarmResponseDto findByAlarmNo (@PathVariable("alarmNo") Long alarmNo) {
        return alarmService.findByAlarmNo(alarmNo);
    }

}
