package com.ohalfmoon.firework.repository;

import com.ohalfmoon.firework.model.AlarmEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.persistence.AlarmRepository;
import com.ohalfmoon.firework.persistence.ApprovalRepository;
import com.ohalfmoon.firework.persistence.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
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
 * packageName    : com.ohalfmoon.firework.repository
 * fileName       : AlarmRepositoryTests
 * author         : 우성준
 * date           : 2023/06/07
 * description    : 알림 Repository 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/07        우성준            최초 생성
 * 2023/06/09        우성준            알람레포지토리 이름 수정
 * 2023/06/12        우성준            알림 리스트 출력 수정(상위 5개만) 및 알림 개수 출력 추가
 * 2023/06/14        우성준            알림 리스트->슬라이스로 변경
 */
@SpringBootTest
@Slf4j
public class AlarmRepositoryTests {
    @Autowired
    private AlarmRepository alarmRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ApprovalRepository approvalRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void saveTests() {
        AlarmEntity alarm = AlarmEntity.builder()
                .alarmTitle("테스트결재안알림14")
                .alarmReceiver(MemberEntity.builder().userNo(1L).build())
                .alarmTitle("테스트결재안알림3")
                .alarmCategory("결제요청")
                .boardNo(null)
                .approvalNo(approvalRepository.findById(3L).orElse(null))
                .build();

        AlarmEntity save = alarmRepository.save(alarm);

        AlarmEntity findAlarm = alarmRepository.findById(save.getAlarmNo()).orElse(null);
        Assertions.assertThat(save).isEqualTo(findAlarm);
        log.info("{}", save.hashCode());
        log.info("{}", findAlarm.hashCode());
        log.info("{}",findAlarm);
//        assertThat(save).isSameAs(findAlarm);
    }

    @Test
    public void findOneTest() {
        Long alarmNo = 51L;

        AlarmEntity findAlarm = alarmRepository.findById(alarmNo).orElse(null);

        Assertions.assertThat(alarmNo).isNotNull();

        log.info("{}", findAlarm);
    }

//    @Test
//    public void findTop5ByAlarmReceiverAndAlarmNoLessThanOrderByAlarmNoDescTest() {
//        Long userNo = 1L;
//
//        List<AlarmEntity> alarmEntityList =
//                alarmRepository.
//                        findTop5ByAlarmReceiverAndAlarmNoLessThanOrderByAlarmNoDesc(memberRepository.
//                                findById(userNo).orElse(null), 55L);
//
//        log.info("{}", alarmEntityList);
//    }

    @Test
    public void deleteAlarmTest() {
        Long alarmNo= 10L;

        alarmRepository.deleteById(alarmNo);

        AlarmEntity alarm = alarmRepository.findById(alarmNo).orElse(null);

        Assertions.assertThat(alarm).isNull();
    }

    @Test
    public void updateAlarmTest() {
        Long alarmNo = 1L;
        boolean alarmCheck = true;

        AlarmEntity alarm = alarmRepository.findById(alarmNo).orElse(null);
        alarm.update(true);
        alarmRepository.save(alarm);
        log.info("{}", alarm);
    }

    @Test
    public void countAlarmByAlarmReceiverTest(){
        Long userNo = 1L;
        Long count = alarmRepository.countAlarmEntitiesByAlarmReceiver(memberRepository.findById(userNo).orElse(null));
        log.info("{}", count);
    }

    @Test
    public void findListBySlice() {
        Pageable pageable = PageRequest.of(0, 3, Sort.Direction.DESC, "alarmReceiver");
        Slice<AlarmEntity> alarmEntities =
                alarmRepository.findSliceByAlarmReceiver(MemberEntity.builder().userNo(1L).build(), pageable);
        log.info("{}", alarmEntities.getContent());
    }
}
