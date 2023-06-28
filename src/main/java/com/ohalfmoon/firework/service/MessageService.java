package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.message.MessageResponseDto;
import com.ohalfmoon.firework.dto.message.MessageSaveDto;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.MessageEntity;
import com.ohalfmoon.firework.persistence.MemberRepository;
import com.ohalfmoon.firework.persistence.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName    : com.ohalfmoon.firework.service
 * fileName       : MessageService
 * author         : 우성준
 * date           : 2023/06/13
 * description    : 쪽지 서비스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/13        우성준           최초 생성
 * 2023/06/19        우성준           페이징 기능 추가
 * 2023/06/26        우성준           검색 기능 보류
 */
@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    // 쪽지 생성
    @Transactional
    public Long save(MessageSaveDto messageSaveDto) {
        return  messageRepository.save(messageSaveDto.toEntity()).getMessageNo();
    }

    // 쪽지 단일 조회
    @Transactional
    public MessageResponseDto findByMessageNo(Long messageNo) {
        MessageEntity message = messageRepository
                .findById(messageNo)
                .orElseThrow(()-> new NullPointerException("존재하지 않은 쪽지입니다"));

        return new MessageResponseDto(message);
    }

    // 쪽지 확인 업데이트
    @Transactional
    public Long update(Long messageNo, boolean messageCheck) {
        MessageEntity messageEntity = messageRepository.findById(messageNo)
                .orElseThrow(()-> new NullPointerException("존재하지 않은 쪽지입니다"));

        messageEntity.update(messageCheck);
        return messageRepository.save(messageEntity).getMessageNo();
    }

    // 쪽지 단일 삭제
    @Transactional
    public void delete(Long messageNo) {
        MessageEntity messageEntity = messageRepository.findById(messageNo)
                .orElseThrow(()-> new NullPointerException("존재하지 않은 쪽지입니다"));

        messageRepository.delete(messageEntity);
    }

    // 미확인 쪽지 개수
    @Transactional
    public Long countMessage(Long receiverNo) {
        return messageRepository.countMessageEntitiesByReceiverAndMessageCheckFalse(MemberEntity.builder().userNo(receiverNo).build());
    }

    // 받은 쪽지 리스트 조회
    public Page<MessageEntity> messageListByReceiver(Long receiver, Pageable pageable) {
        return messageRepository.findAllByReceiver(MemberEntity.builder().userNo(receiver).build(),pageable);
    }

    // 보낸 쪽지 리스트 조회
    public Page<MessageEntity> messageListBySender(Long sender, Pageable pageable) {
        return messageRepository.findAllBySender(MemberEntity.builder().userNo(sender).build(),pageable);
    }

    // 선택된 쪽지들 삭제
    @Transactional
    public void deleteAll(List<Long> arrMessage) {
        messageRepository.deleteAll(arrMessage.stream().map(m->MessageEntity.builder().messageNo(m).build()).collect(Collectors.toList()));
    }
}
