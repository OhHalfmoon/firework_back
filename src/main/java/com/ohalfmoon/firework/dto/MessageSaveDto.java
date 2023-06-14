package com.ohalfmoon.firework.dto;

import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.MessageEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.ohalfmoon.firework.dto
 * fileName       : MessageSaveDto
 * author         : 우성준
 * date           : 2023/06/13
 * description    : 쪽지등록 Dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/13        우성준           최초 생성
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageSaveDto {
    private Long receiver;

    private Long sender;

    private String messageTitle;

    private String messageContent;

    public MessageEntity toEntity(){
        return MessageEntity.builder()
                .receiver(MemberEntity.builder().userNo(receiver).build())
                .sender(MemberEntity.builder().userNo(sender).build())
                .messageTitle(messageTitle)
                .messageContent(messageContent)
                .build();
    }
}
