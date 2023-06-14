package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.AlarmResponseDto;
import com.ohalfmoon.firework.dto.AlarmSaveDto;
import com.ohalfmoon.firework.dto.member.MemberDTO;
import com.ohalfmoon.firework.dto.member.MemberLoginDTO;
import com.ohalfmoon.firework.service.AlarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
 */
@RequiredArgsConstructor
@RequestMapping("/api/alarm")
@RestController
@Slf4j
public class AlarmApiController {
    private final AlarmService alarmService;

    // 사용자의 따른 알림 리스트 출력
    @GetMapping({"/member/{userNo}","/member/{userNo}/{alarmNo}"})
    public List<AlarmResponseDto>findTop5ByAlarmReceiver(@AuthenticationPrincipal MemberLoginDTO memberLoginDTO, @PathVariable Long userNo, @PathVariable(required = false) Optional<Long> alarmNo) {
        log.info("{}", memberLoginDTO);
        return alarmService.findTop5ByAlarmReceiver(userNo, alarmNo.orElse(0L));
    }


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

}
