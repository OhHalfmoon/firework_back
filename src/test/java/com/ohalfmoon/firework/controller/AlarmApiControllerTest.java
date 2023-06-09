package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.AlarmSaveDto;
import com.ohalfmoon.firework.model.AlarmEntity;
import com.ohalfmoon.firework.model.ApprovalEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.persistence.AlarmRepository;
import com.ohalfmoon.firework.service.AlarmService;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
/**
 * packageName    : com.ohalfmoon.firework.controller
 * fileName       : AlarmApiControllerTest
 * author         : 우성준
 * date           : 2023/06/09
 * description    : 알림 API 컨트롤러 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/09        우성준            최초 생성
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class AlarmApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AlarmRepository alarmRepository;

    @Autowired
    private AlarmService alarmService;
//    @After
//    public void tearDown() throws Exception {
//        alarmRepository.deleteAll();
//    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void saveTest() throws Exception {
        String alarmCategory = "결제요청";
        String alarmTitle = "새로운 결재요청이 들어왔습니다";
        AlarmSaveDto alarmSaveDto = AlarmSaveDto.builder()
                .userNo(1L)
                .alarmCategory(alarmCategory)
                .alarmTitle(alarmTitle)
                .boardNo(null)
                .approvalNo(3L)
                .build();

        String url = "http://localhost:" + port + "/api/alarm/";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, alarmSaveDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<AlarmEntity> all = alarmRepository.findAll();
        log.info("{}", all.get(0));
        assertThat(all.get(0).getAlarmTitle()).isEqualTo(alarmTitle);
        assertThat(all.get(0).getAlarmCategory()).isEqualTo(alarmCategory);
    }
    // 나머지 기능들은 Postman으로 진행
}
