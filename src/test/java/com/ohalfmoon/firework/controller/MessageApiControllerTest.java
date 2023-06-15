package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.MessageSaveDto;
import com.ohalfmoon.firework.model.MessageEntity;
import com.ohalfmoon.firework.persistence.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

/**
 * packageName    : com.ohalfmoon.firework.controller
 * fileName       : MessageApiControllerTest
 * author         : 우성준
 * date           : 2023/06/14
 * description    : 쪽지 API 컨트롤러 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/14        우성준            최초 생성
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class MessageApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MessageRepository messageRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void saveTest() throws Exception {
        String messageTitle = "쪽지테스트제목";
        String messageContent = "쪽지테스트내용";

        MessageSaveDto messageSaveDto = MessageSaveDto.builder()
                .receiver(2L)
                .sender(1L)
                .messageContent(messageContent)
                .messageTitle(messageTitle)
                .build();

        String url = "http://localhost:" + port + "/api/message/";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, messageSaveDto, Long.class);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(14L);

        List<MessageEntity> all = messageRepository.findAll();
        Assertions.assertThat(all.get(12).getMessageTitle()).isEqualTo(messageTitle);
        Assertions.assertThat(all.get(12).getMessageContent()).isEqualTo(messageContent);
    }
    // 나머지 기능들은 Postman으로 진행
}
