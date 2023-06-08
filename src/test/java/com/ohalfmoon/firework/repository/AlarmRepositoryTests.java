package com.ohalfmoon.firework.repository;

import com.ohalfmoon.firework.model.AlarmEntity;
import com.ohalfmoon.firework.model.ApprovalEntity;
import com.ohalfmoon.firework.model.FormEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.persistence.AlamRepository;
import com.ohalfmoon.firework.persistence.ApprovalRepository;
import com.ohalfmoon.firework.persistence.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName    : com.ohalfmoon.firework.repository
 * fileName       : AlarmRepositoryTests
 * author         : 우성준
 * date           : 2023/06/07
 * description    : 알림 Repository 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/07        우성준            최초 생성
 */
@SpringBootTest
@Slf4j
public class AlarmRepositoryTests {
    @Autowired
    private AlamRepository alamRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ApprovalRepository approvalRepository;

    @Test
    @Transactional
    public void saveTests() {
        AlarmEntity alarm = AlarmEntity.builder()
                .alarmTitle("테스트결재안알림13")
                .alarmReceiver(memberRepository.findById(1L).orElse(null))
                .alarmTitle("테스트결재안알림2")
                .alarmReceiver(MemberEntity.builder().userNo(1L).build())
                .alarmCategory("결제요청")
                .boardNo(null)
                .approvalNo(approvalRepository.findById(3L).orElse(null))
                .build();

        AlarmEntity save = alamRepository.save(alarm);

        AlarmEntity findAlarm = alamRepository.findById(save.getAlarmNo()).orElse(null);
        Assertions.assertThat(save).isEqualTo(findAlarm);
        log.info("{}", save.hashCode());
        log.info("{}", findAlarm.hashCode());
//        assertThat(save).isSameAs(findAlarm);
    }

    @Test
    public void findOneTest() {
        Long alarmNo = 6L;

        AlarmEntity findAlarm = alamRepository.findById(alarmNo).orElse(null);

        Assertions.assertThat(alarmNo).isNotNull();

        log.info("{}", findAlarm);
    }

    @Test
    public void findByUserNoTest() {
        Long userNo = 1L;

        List<AlarmEntity> alarmEntityList = alamRepository.findAllByUserNo(1L);
    }
}
