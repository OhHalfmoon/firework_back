package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.AlarmResponseDto;
import com.ohalfmoon.firework.dto.AlarmSaveDto;
import com.ohalfmoon.firework.model.AlarmEntity;
import com.ohalfmoon.firework.persistence.AlamRepository;
import com.ohalfmoon.firework.persistence.ApprovalRepository;
import com.ohalfmoon.firework.persistence.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName    : com.ohalfmoon.firework.service
 * fileName       : AlarmServiceTest
 * author         : 우성준
 * date           : 2023/06/08
 * description    : 알림 Service 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/08        우성준           최초 생성
 */
@SpringBootTest
@Slf4j
public class AlarmServiceTest {
    @Autowired
    private AlarmService alarmService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ApprovalRepository approvalRepository;

    @Autowired
    private AlamRepository alamRepository;

    @Test
    @DisplayName("저장 테스트")
    @Transactional
    @Rollback(value = false)
    void saveTest() {
        AlarmSaveDto alarmSaveDto = AlarmSaveDto.builder()
                .userNo(2L)
                .alarmCategory("결제요청")
                .alarmTitle("테스트기안제목")
                .approvalNo(3L)
                .boardNo(null)
                .build();

        Long saveId = alarmService.save(alarmSaveDto);

        AlarmEntity alarm = alamRepository.findById(saveId).orElse(null);

        assertThat(alarm).isNotNull();

        log.info("{}", alarm);
    }

    @Test
    @DisplayName("단일조회 테스트")
    @Transactional
    void findByAlarmNoTest() {
        Long alarmNo = 1L;
        AlarmResponseDto byAlarmNo = alarmService.findByAlarmNo(alarmNo);
        log.info("{}",byAlarmNo);
        AlarmResponseDto alarmResponseDto = new AlarmResponseDto(alamRepository.findById(alarmNo).orElse(new AlarmEntity()));
        assertThat(byAlarmNo).isEqualTo(alarmResponseDto);
    }

    @Test
    @DisplayName("리스트조회 테스트")
    @Transactional
    void findAllByAlarmReceiverTest() {
        List<AlarmResponseDto> list = alarmService.findAllByAlarmReceiver(com.ohalfmoon.firework.dto.form.MemberDTO.builder().userNo(1L).build());
        log.info("{}", list);
    }

    @Test
    @DisplayName("수정 테스트")
    @Transactional
    @Rollback(value = false)
    void updateTest() {
        Long alarmNo = 2L;
        AlarmEntity alarmEntity = alamRepository.findById(alarmNo).orElse(null);

        Long updateAlarmNo = alarmService.update(alarmNo, true);
        AlarmEntity updateAlarmEntity = alamRepository.findById(updateAlarmNo).orElse(null);

        assertThat(alarmEntity).isEqualTo(updateAlarmEntity);
    }

    @Test
    @DisplayName("삭제 테스트")
    @Transactional
    @Rollback(value = false)
    void deleteTest() {
        Long alarmNo = 38L;

        alarmService.delete(alarmNo);

        assertThat(alamRepository.findById(alarmNo).orElse(null)).isNull();
    }
}
