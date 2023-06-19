package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.MessageResponseDto;
import com.ohalfmoon.firework.dto.MessageSaveDto;
import com.ohalfmoon.firework.model.MessageEntity;
import com.ohalfmoon.firework.persistence.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

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
public class MessageServiceTest {
    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageRepository messageRepository;

    @Test
    @DisplayName("저장 테스트")
    @Transactional
    @Rollback(value = false)
    void saveTest() {
        MessageSaveDto messageSaveDto = MessageSaveDto.builder()
                .receiver(2L)
                .sender(1L)
                .messageTitle("쪽지테스트제목")
                .messageContent("쪽지테스트내용")
                .build();

        Long saveId = messageService.save(messageSaveDto);

        MessageEntity message = messageRepository.findById(saveId).orElse(null);

        Assertions.assertThat(message).isNotNull();

        log.info("{}", message);
    }

    @Test
    @DisplayName("단일조회 테스트")
    @Transactional
    void findByMessageNoTest() {
        MessageResponseDto byMessageNo = messageService.findByMessageNo(2L);
        log.info("{}", byMessageNo);
        MessageResponseDto messageResponseDto =
                new MessageResponseDto(messageRepository.findById(2L).orElse(new MessageEntity()));
        Assertions.assertThat(byMessageNo).isEqualTo(messageResponseDto);
    }

    @Test
    @DisplayName("수정 테스트")
    @Transactional
    @Rollback(value = false)
    void updateTest() {
        MessageEntity messageEntity = messageRepository.findById(2L).orElse(null);

        Long updateMessageNo = messageService.update(2L, true);
        MessageEntity updateMessageEntity = messageRepository.findById(updateMessageNo).orElse(null);

        Assertions.assertThat(messageEntity).isEqualTo(updateMessageEntity);
    }

    @Test
    @DisplayName("삭제 테스트")
    @Transactional
    @Rollback(value = false)
    void deleteTest() {
        messageService.delete(2L);
        Assertions.assertThat(messageRepository.findById(2L).orElse(null)).isNull();
    }

    @Test
    @DisplayName("페이징 리스트 테스트")
    @Transactional
    @Rollback(value = false)
    void pagingTest() {
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "messageNo");
        Page<MessageEntity> dtos = messageService.messageListBySender(1L, pageable);
        log.info("{}", dtos.getContent());
    }
}
