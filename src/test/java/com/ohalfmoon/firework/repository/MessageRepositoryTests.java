package com.ohalfmoon.firework.repository;


import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.MessageEntity;
import com.ohalfmoon.firework.persistence.MemberRepository;
import com.ohalfmoon.firework.persistence.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.ohalfmoon.firework.repository
 * fileName       : MessageRepositoryTests
 * author         : 우성준
 * date           : 2023/06/13
 * description    : 쪽지 Repository 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/13        우성준            최초 생성
 */

@SpringBootTest
@Slf4j
public class MessageRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void saveTests() {
        MessageEntity message = MessageEntity.builder()
                .messageTitle("테스트쪽지제목")
                .messageContent("테스트쪽지내용")
                .receiver(memberRepository.findById(1L).orElse(null))
                .sender(memberRepository.findById(2L).orElse(null))
                .build();

        MessageEntity save = messageRepository.save(message);

        MessageEntity findMessage = messageRepository.findById(save.getMessageNo()).orElse(null);
        Assertions.assertThat(save).isEqualTo(findMessage);
        log.info("{}", save.hashCode());
        log.info("{}", findMessage.hashCode());
        log.info("{}", findMessage);
    }

    @Test
    public void findOneTest() {
        MessageEntity findMessage = messageRepository.findById(1L).orElse(null);
        Assertions.assertThat(1L).isNotNull();
        log.info("{}", findMessage);
    }


    @Test
    public void deleteMessageTest() {
        messageRepository.deleteById(11L);
        MessageEntity message = messageRepository.findById(11L).orElse(null);
        Assertions.assertThat(message).isNull();
    }

    @Test
    public void updateMessageTest() {
        MessageEntity message = messageRepository.findById(1L).orElse(null);
        message.update(true);
        messageRepository.save(message);
        log.info("{}", message);
    }

    @Test
    public void countMessageTest(){
        Long count = messageRepository.countMessageEntitiesByReceiverAndMessageCheckFalse(memberRepository.findById(1L).orElse(null));
        log.info("{}", count);
    }

    @Test
    @Transactional
    @DisplayName("쪽지 페이징 테스트")
    @Rollback(value = false)
    public void pagingTest() {
        List<MessageEntity> messageEntities = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            MessageEntity input = MessageEntity.builder()
                    .receiver(MemberEntity.builder().userNo(2L).build())
                    .sender(MemberEntity.builder().userNo(1L).build())
                    .messageTitle("테스트 쪽지 제목" + i)
                    .messageContent("테스트 쪽지 내용" + i)
                    .build();

            messageEntities.add(input);
        }
        messageRepository.saveAll(messageEntities);

        PageRequest pageRequest = PageRequest.of(0, 5, Sort.Direction.DESC, "messageNo");

        Page<MessageEntity> entities = messageRepository.findAllBySender(MemberEntity.builder()
                .userNo(1L)
                .build(), pageRequest);

        log.info("{}", entities.getContent());
    }
}
