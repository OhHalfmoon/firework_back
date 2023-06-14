package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.AlarmResponseDto;
import com.ohalfmoon.firework.dto.AlarmSaveDto;
import com.ohalfmoon.firework.model.AlarmEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.persistence.AlarmRepository;
import com.ohalfmoon.firework.persistence.ApprovalRepository;
import com.ohalfmoon.firework.persistence.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
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
 * 2023/06/12        우성준           알림 리스트 출력 수정(상위 5개만) 및 알림 개수 출력 추가
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
    private AlarmRepository alarmRepository;

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

        AlarmEntity alarm = alarmRepository.findById(saveId).orElse(null);

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
        AlarmResponseDto alarmResponseDto = new AlarmResponseDto(alarmRepository.findById(alarmNo).orElse(new AlarmEntity()));
        assertThat(byAlarmNo).isEqualTo(alarmResponseDto);
    }

    @Test
    @DisplayName("리스트조회 테스트")
    @Transactional
    void findTop5ByAlarmReceiverAndAlarmNoLessThanOrderByAlarmNoDescTest() {
        List<AlarmResponseDto> list = alarmService.findTop5ByAlarmReceiver(1L, 55L);
        log.info("{}", list);
    }

    @Test
    @DisplayName("수정 테스트")
    @Transactional
    @Rollback(value = false)
    void updateTest() {
        Long alarmNo = 2L;
        AlarmEntity alarmEntity = alarmRepository.findById(alarmNo).orElse(null);

        Long updateAlarmNo = alarmService.update(alarmNo, true);
        AlarmEntity updateAlarmEntity = alarmRepository.findById(updateAlarmNo).orElse(null);

        assertThat(alarmEntity).isEqualTo(updateAlarmEntity);
    }

    @Test
    @DisplayName("삭제 테스트")
    @Transactional
    @Rollback(value = false)
    void deleteTest() {
        Long alarmNo = 38L;

        alarmService.delete(alarmNo);

        assertThat(alarmRepository.findById(alarmNo).orElse(null)).isNull();
    }

    @Test
    @DisplayName("슬라이스 리스트 테스트")
    @Transactional
    @Rollback(value = false)
    void findListBySlice() {
        Pageable pageable = PageRequest.of(1, 5, Sort.Direction.DESC, "alarmNo");
        Slice<AlarmResponseDto> slice = alarmService.findListBySlice(1L, pageable);
        log.info("{}", slice.getContent());
    }

}
