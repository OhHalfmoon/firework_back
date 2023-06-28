package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.alarm.AlarmResponseDto;
import com.ohalfmoon.firework.dto.alarm.AlarmSaveDto;
import com.ohalfmoon.firework.service.AlarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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
 * 2023/06/12        우성준            알림 리스트 출력 수정(상위 5개만) 및 알림 개수 출력 추가
 * 2023/06/14        우성준            알림 리스트->슬라이스로 변경
 */
@RequiredArgsConstructor
@RequestMapping("/api/alarm")
@RestController
@Slf4j
public class AlarmApiController {
    private final AlarmService alarmService;

    // 알림 개수 출력
    @GetMapping("/count/{userNo}")
    public Long countAlarmByAlarmReceiver(@PathVariable Long userNo) {
        return alarmService.countAlarmByAlarmReceiver(userNo);
    }

    // 알림 생성
    @PostMapping("/")
    public Long save(@RequestBody AlarmSaveDto alarmSaveDto) {
        return alarmService.save(alarmSaveDto);
    }

    // 알림 읽음 상태처리
    @PutMapping("/{alarmNo}")
    public Long update(@PathVariable Long alarmNo, @RequestBody Map<String,  Boolean> map) {
        log.info("{}", map);
        return alarmService.update(alarmNo, map.get("alarmCheck"));
    }

    // 알림 삭제
    @DeleteMapping("/{alarmNo}")
    public Long delete(@PathVariable Long alarmNo) {
        alarmService.delete(alarmNo);
        return alarmNo;
    }

    // 알림 단일 조회
    @GetMapping("/{alarmNo}")
    public AlarmResponseDto findByAlarmNo (@PathVariable("alarmNo") Long alarmNo) {
        return alarmService.findByAlarmNo(alarmNo);
    }

    // 사용자의 따른 알림 리스트(슬라이스) 출력
    @GetMapping("/member/{userNo}")
    public Slice<AlarmResponseDto> findListBySlice(@PathVariable Long userNo, @PageableDefault(size = 5, sort = "alarmNo",direction = Sort.Direction.DESC) Pageable pageable) {
        return alarmService.findListBySlice(userNo,pageable);
    }
}
