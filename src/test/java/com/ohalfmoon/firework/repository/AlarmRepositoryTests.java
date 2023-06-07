package com.ohalfmoon.firework.repository;

import com.ohalfmoon.firework.model.AlarmEntity;
import com.ohalfmoon.firework.model.ApprovalEntity;
import com.ohalfmoon.firework.model.FormEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.persistence.AlamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
public class AlarmRepositoryTests {
    @Autowired
    private AlamRepository alamRepository;

    @Test
    public void saveTests() {
        AlarmEntity alarm = AlarmEntity.builder()
                .alarmTitle("테스트결재안알림9")
                .alarmReceiver(MemberEntity.builder().userNo(1L).build())
                .alarmCategory("결제요청")
                .boardNo(null)
                .approvalNo(ApprovalEntity.builder().approvalNo(3L).build())
                .build();

        AlarmEntity save = alamRepository.save(alarm);

        AlarmEntity findAlarm = alamRepository.findById(save.getAlarmNo()).orElse(null);

        assertThat(save).isEqualTo(findAlarm);
    }

//    @Test
//    public void findOneTest() {
//        Long formNo = 6L;
//
//        AlarmEntity findAlarm = alamRepository.findById(alarmNo).orElse(null);
//
//        Assertions.assertThat(findForm).isNotNull();
//    }
}
