package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.MessageResponseDto;
import com.ohalfmoon.firework.dto.MessageSaveDto;
import com.ohalfmoon.firework.model.MessageEntity;
import com.ohalfmoon.firework.persistence.MemberRepository;
import com.ohalfmoon.firework.persistence.MessageRepository;
import lombok.RequiredArgsConstructor;
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
 */
@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    private final MemberRepository memberRepository;

    @Transactional
    public Long save(MessageSaveDto messageSaveDto) {
        return  messageRepository.save(messageSaveDto.toEntity()).getMessageNo();
    }

    @Transactional
    public MessageResponseDto findByMessageNo(Long messageNo) {
        MessageEntity message = messageRepository
                .findById(messageNo)
                .orElseThrow(()-> new NullPointerException("존재하지 않은 쪽지입니다"));

        return new MessageResponseDto(message);
    }

    @Transactional
    public List<MessageResponseDto> findListByReceiver(Long receiverNo, Long messageNo) {
        return messageRepository
                .findTop5ByReceiverAndMessageNoLessThanOrderByMessageNoDesc(memberRepository
                        .findById(receiverNo).orElse(null), messageNo)
                            .stream().map(MessageResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public Long update(Long messageNo, boolean messageCheck) {
        MessageEntity messageEntity = messageRepository.findById(messageNo)
                .orElseThrow(()-> new NullPointerException("존재하지 않은 쪽지입니다"));

        messageEntity.update(messageCheck);
        return messageRepository.save(messageEntity).getMessageNo();
    }

    @Transactional
    public void delete(Long messageNo) {
        MessageEntity messageEntity = messageRepository.findById(messageNo)
                .orElseThrow(()-> new NullPointerException("존재하지 않은 쪽지입니다"));

        messageRepository.delete(messageEntity);
    }

    @Transactional
    public Long countMessage(Long receiverNo) {
        return messageRepository.countMessageEntitiesByReceiverAndMessageCheckFalse(memberRepository.findById(receiverNo).orElse(null));
    }

    @Transactional
    public List<MessageResponseDto> findListBySender(Long senderNo, Long messageNo) {
        return messageRepository
                .findTop5BySenderAndMessageNoLessThanOrderByMessageNoDesc(memberRepository
                        .findById(senderNo).orElse(null), messageNo)
                .stream().map(MessageResponseDto::new).collect(Collectors.toList());
    }


}
